# 字段监控设计
- 需求描述: 一个实体的字段可能会有后续的行为.

- 字段有什么变化会有什么样的操作
    1. 字段变动.
        1. 常见新老数据变更的日志记录
    2. 字段直接会产生行为
        1. 发消息的场景.填写了用户id就发送消息
        
- 上述为目前想到的字段监控可能存在的场景. 针对这样的场景做出如下设计.

- 接口抽象

```java
public interface MonitoringInterface<T> {

	void monitor(T t);

}

/**
 * 监控:字段变更.
 * 字段变更后需要做的事情
 */
public interface MonitoringFieldChange<T> {

	void filedChange(T t);

}

/**
 * 监控,通过字段决定后续工作.
 * 例如传递这个值会后续执行什么事情.
 */
public interface MonitoringFieldWork<T> {

	void fieldWork(T t);
}

```

- 三个接口的作用
    1. MonitoringInterface 整体监控
    1. MonitoringFieldChange 字段变更的监控
    1. MonitoringFieldWork 字段决定行为的监控
    
    
- 综合前面的需求描述做出一个通用定义. 
    - 传入对象, 通过这个对象去调用 **字段变更监控方法** 和 **字段决定行为的监控方法**
    
    
```java

public class SupperMonitor<T> {

	private final T data;

	private MonitoringFieldChange<T> fieldChange;

	private MonitoringFieldWork<T> fieldWork;

	public SupperMonitor(T data, MonitoringFieldChange<T> fieldChange, MonitoringFieldWork<T> fieldWork) {
		this.data = data;
		this.fieldChange = fieldChange;
		this.fieldWork = fieldWork;
	}

	public void monitor() {
		this.fieldChange.filedChange(data);
		this.fieldWork.fieldWork(data);
	}

}

```


- 在这里还可以用工程去创建内部变量**fieldChange**和**fieldWork**
- 暂且称**SupperMonitor**是一个统筹调用对象.接下来是几个接口的实现
    在实现之前定义出如下两个接口. 用来做类型限定. 
    下面两个接口内部填写的抽象方法是我们需要比较的字段或者需要根据某个字段去做操作的抽象
    将不同的字段处理方案放在两个不同的接口中,将行为逻辑隔离.
```java
public interface FirstFieldChangeMonitoring extends MonitoringFieldChange<FirstModel> {

	void nameChangeMonitor(String oldName, String newName);


	default Class<?> type() {
		return FirstModel.class;
	}
}

public interface FirstFieldWorkMonitoring extends MonitoringFieldWork<FirstModel> {
	void workByWorkField(boolean work);

	default Class<?> type() {
		return FirstModel.class;
	}
}

```


- 实现以及调用

```java
public class FirstFieldChangeMonitoringImpl implements MonitoringInterface<FirstModel>, FirstFieldWorkMonitoring, FirstFieldChangeMonitoring {
	@Override
	public void nameChangeMonitor(String oldName, String newName) {
		System.out.println("数据变更" + oldName + "\t" + newName);
	}

	@Override
	public void filedChange(FirstModel firstModel) {
		this.nameChangeMonitor("", firstModel.getName());
	}

	@Override
	public void workByWorkField(boolean work) {
		if (work) {
			System.out.println("开始工作");
		}
	}

	@Override
	public Class<?> type() {
		return FirstModel.class;
	}

	@Override
	public void fieldWork(FirstModel firstModel) {
		this.workByWorkField(firstModel.isWork());
	}

	@Override
	public void monitor(FirstModel firstModel) {
		SupperMonitor<FirstModel> firstModelSupperMonitor = new SupperMonitor<>(firstModel, this, this);

		firstModelSupperMonitor.monitor();
	}

	public static void main(String[] args) {
		FirstFieldChangeMonitoringImpl firstFieldChangeMonitoring = new FirstFieldChangeMonitoringImpl();
		FirstModel firstModel = new FirstModel();
		firstModel.setName("aaaaaa");
		firstModel.setWork(true);
		firstFieldChangeMonitoring.monitor(firstModel);
	}
}

```


- 接下来就是一个配合使用的技巧. 
    先做出第二个对象的一个监控实现
    
    
    
```

public interface SecondFieldWorkMonitoring extends MonitoringFieldWork<SecondModel> {
	void workByWorkField(boolean work);

	default Class<?> type() {
		return SecondModel.class;
	}
}


public class SecondFieldWorkMonitoringImpl implements SecondFieldWorkMonitoring {
	@Override
	public void workByWorkField(boolean work) {
		System.out.println("第二个model的work");

	}

	@Override
	public void fieldWork(SecondModel secondModel) {
		this.workByWorkField(secondModel.isWorking());
	}
}

```

- 补充监控字段, 测试和实现如下

```java

public class FirstFieldChangeMonitoringImpl implements MonitoringInterface<FirstModel>, FirstFieldWorkMonitoring, FirstFieldChangeMonitoring {
	public static void main(String[] args) {
		FirstFieldChangeMonitoringImpl firstFieldChangeMonitoring = new FirstFieldChangeMonitoringImpl();
		SecondModel secondModel = new SecondModel();
		secondModel.setWorking(false);

		FirstModel firstModel = new FirstModel();
		firstModel.setName("aaaaaa");
		firstModel.setWork(true);
		firstModel.setSecondModel(secondModel);

		firstFieldChangeMonitoring.monitor(firstModel);
	}

	@Override
	public void nameChangeMonitor(String oldName, String newName) {
		System.out.println("数据变更" + oldName + "\t" + newName);
	}

	@Override
	public void filedChange(FirstModel firstModel) {
		// 字段变动任务
		this.nameChangeMonitor("", firstModel.getName());
	}

	@Override
	public void workByWorkField(boolean work) {
		if (work) {
			System.out.println("开始工作");
		}
	}

	@Override
	public void workBySecondField(SecondModel secondModel) {
		SupperMonitor<SecondModel> secondModelSupperMonitor = new SupperMonitor<>(secondModel);
		secondModelSupperMonitor.monitor();
	}

	@Override
	public Class<?> type() {
		return FirstModel.class;
	}

	@Override
	public void fieldWork(FirstModel firstModel) {
		// 字段行为任务
		this.workByWorkField(firstModel.isWork());
		this.workBySecondField(firstModel.getSecondModel());
	}

	@Override
	public void monitor(FirstModel firstModel) {
		SupperMonitor<FirstModel> firstModelSupperMonitor = new SupperMonitor<>(firstModel, this, this);

		firstModelSupperMonitor.monitor();
	}
}


```


- 这是目前笔者的一个想法. 各位阅读者如果有想法请在评论中告知. 

项目地址: https://github.com/huifer/javaBook-src.git 