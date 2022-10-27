# Sql 链接查询

现有两张表



![image-20200327082821430](assets/image-20200327082821430.png)



![image-20200327082827741](assets/image-20200327082827741.png)





## 实例

- `select * from t_demo left join t_demo_2 on 1=1 `



![image-20200327082919851](assets/image-20200327082919851.png)	



- 此时结果为笛卡儿积



如果我在后面使用where 条件 去操作 id  那么 会有多条记录

![image-20200327083015603](assets/image-20200327083015603.png)

此时我要去求和字段或者一些group 这样的操作得到一个结果，数据明显是不对的 ，只有一条现在多了一条出来





![image-20200327083123423](assets/image-20200327083123423.png)

此时才保证了左侧表的完整性，不多不少



继续补充 where 条件



![image-20200327083159061](assets/image-20200327083159061.png)







right join 同理



![image-20200327083228169](assets/image-20200327083228169.png)