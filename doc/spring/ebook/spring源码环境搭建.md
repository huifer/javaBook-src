# spring 源码环境搭建
- Author: [HuiFer](https://github.com/huifer)
## 前置准备
### 源码获取
- 获取一份[spring framework](https://github.com/spring-projects/spring-framework)源码.
    - 使用的分支为 `5.1.x`
### JDK
- 准备JDK. 笔者准备的版本如下
```text
java version "1.8.0_121"
Java(TM) SE Runtime Environment (build 1.8.0_121-b13)
Java HotSpot(TM) 64-Bit Server VM (build 25.121-b13, mixed mode)
```
### gradle
- 准备 Gradle . **spring framework**是基于gradle进行开发的一个项目,gradle版本选择变成了一个问题. 
- 第一次尝试使用了最新的Gradle(6.0.1)进行编译没有通过

```
exception during working with external system: java.lang.AssertionError
	at org.jetbrains.plugins.gradle.service.project.BaseGradleProjectResolverExtension.createModule(BaseGradleProjectResolverExtension.java:154)
	at org.jetbrains.plugins.gradle.service.project.AbstractProjectResolverExtension.createModule(AbstractProjectResolverExtension.java:86)
	at org.jetbrains.kotlin.idea.configuration.KotlinMPPGradleProjectResolver.createModule(KotlinMPPGradleProjectResolver.kt:67)
	at org.jetbrains.plugins.gradle.service.project.AbstractProjectResolverExtension.createModule(AbstractProjectResolverExtension.java:86)
	at org.jetbrains.plugins.gradle.service.project.AbstractProjectResolverExtension.createModule(AbstractProjectResolverExtension.java:86)
	at org.jetbrains.plugins.gradle.service.project.AbstractProjectResolverExtension.createModule(AbstractProjectResolverExtension.java:86)
	at org.jetbrains.plugins.gradle.service.project.AbstractProjectResolverExtension.createModule(AbstractProjectResolverExtension.java:86)
	at org.jetbrains.plugins.gradle.service.project.AbstractProjectResolverExtension.createModule(AbstractProjectResolverExtension.java:86)
	at org.jetbrains.plugins.gradle.service.project.AbstractProjectResolverExtension.createModule(AbstractProjectResolverExtension.java:86)
	at org.jetbrains.plugins.gradle.service.project.AbstractProjectResolverExtension.createModule(AbstractProjectResolverExtension.java:86)
	at org.jetbrains.plugins.gradle.service.project.AbstractProjectResolverExtension.createModule(AbstractProjectResolverExtension.java:86)
	at org.jetbrains.plugins.gradle.service.project.AbstractProjectResolverExtension.createModule(AbstractProjectResolverExtension.java:86)
	at org.jetbrains.plugins.gradle.service.project.AbstractProjectResolverExtension.createModule(AbstractProjectResolverExtension.java:86)
	at org.jetbrains.plugins.gradle.service.project.AbstractProjectResolverExtension.createModule(AbstractProjectResolverExtension.java:86)
	at org.jetbrains.plugins.gradle.service.project.AbstractProjectResolverExtension.createModule(AbstractProjectResolverExtension.java:86)
	at org.jetbrains.plugins.gradle.service.project.TracedProjectResolverExtension.createModule(TracedProjectResolverExtension.java:45)
	at org.jetbrains.plugins.gradle.service.project.GradleProjectResolver.doResolveProjectInfo(GradleProjectResolver.java:344)
	at org.jetbrains.plugins.gradle.service.project.GradleProjectResolver.access$200(GradleProjectResolver.java:60)
	at org.jetbrains.plugins.gradle.service.project.GradleProjectResolver$ProjectConnectionDataNodeFunction.fun(GradleProjectResolver.java:725)
	at org.jetbrains.plugins.gradle.service.project.GradleProjectResolver$ProjectConnectionDataNodeFunction.fun(GradleProjectResolver.java:708)
	at org.jetbrains.plugins.gradle.service.execution.GradleExecutionHelper.execute(GradleExecutionHelper.java:278)
	at org.jetbrains.plugins.gradle.service.project.GradleBuildSrcProjectsResolver.handleBuildSrcProject(GradleBuildSrcProjectsResolver.java:185)
	at org.jetbrains.plugins.gradle.service.project.GradleBuildSrcProjectsResolver.discoverAndAppendTo(GradleBuildSrcProjectsResolver.java:152)
	at org.jetbrains.plugins.gradle.service.project.GradleProjectResolver.resolveProjectInfo(GradleProjectResolver.java:131)
	at org.jetbrains.plugins.gradle.service.project.GradleProjectResolver.resolveProjectInfo(GradleProjectResolver.java:60)
	at com.intellij.openapi.externalSystem.service.remote.RemoteExternalSystemProjectResolverImpl.lambda$resolveProjectInfo$0(RemoteExternalSystemProjectResolverImpl.java:35)
	at com.intellij.openapi.externalSystem.service.remote.AbstractRemoteExternalSystemService.execute(AbstractRemoteExternalSystemService.java:57)
	at com.intellij.openapi.externalSystem.service.remote.RemoteExternalSystemProjectResolverImpl.resolveProjectInfo(RemoteExternalSystemProjectResolverImpl.java:35)
	at com.intellij.openapi.externalSystem.service.remote.wrapper.ExternalSystemProjectResolverWrapper.resolveProjectInfo(ExternalSystemProjectResolverWrapper.java:44)
	at com.intellij.openapi.externalSystem.service.internal.ExternalSystemResolveProjectTask.doExecute(ExternalSystemResolveProjectTask.java:100)
	at com.intellij.openapi.externalSystem.service.internal.AbstractExternalSystemTask.execute(AbstractExternalSystemTask.java:146)
	at com.intellij.openapi.externalSystem.service.internal.AbstractExternalSystemTask.execute(AbstractExternalSystemTask.java:132)
	at com.intellij.openapi.externalSystem.util.ExternalSystemUtil$3.executeImpl(ExternalSystemUtil.java:540)
	at com.intellij.openapi.externalSystem.util.ExternalSystemUtil$3.lambda$execute$1(ExternalSystemUtil.java:392)
	at com.intellij.openapi.project.DumbServiceImpl.suspendIndexingAndRun(DumbServiceImpl.java:150)
	at com.intellij.openapi.externalSystem.util.ExternalSystemUtil$3.execute(ExternalSystemUtil.java:392)
	at com.intellij.openapi.externalSystem.util.ExternalSystemUtil$5.run(ExternalSystemUtil.java:647)
	at com.intellij.openapi.progress.impl.CoreProgressManager$TaskRunnable.run(CoreProgressManager.java:888)
	at com.intellij.openapi.progress.impl.CoreProgressManager.lambda$runProcess$2(CoreProgressManager.java:163)
	at com.intellij.openapi.progress.impl.CoreProgressManager.registerIndicatorAndRun(CoreProgressManager.java:585)
	at com.intellij.openapi.progress.impl.CoreProgressManager.executeProcessUnderProgress(CoreProgressManager.java:531)
	at com.intellij.openapi.progress.impl.ProgressManagerImpl.executeProcessUnderProgress(ProgressManagerImpl.java:59)
	at com.intellij.openapi.progress.impl.CoreProgressManager.runProcess(CoreProgressManager.java:150)
	at com.intellij.openapi.progress.impl.CoreProgressManager$4.lambda$run$0(CoreProgressManager.java:402)
	at com.intellij.util.ConcurrencyUtil.runUnderThreadName(ConcurrencyUtil.java:221)
	at com.intellij.openapi.progress.impl.CoreProgressManager$4.run(CoreProgressManager.java:402)
	at com.intellij.openapi.application.impl.ApplicationImpl$1.run(ApplicationImpl.java:238)
	at java.base/java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:515)
	at java.base/java.util.concurrent.FutureTask.run(FutureTask.java:264)
	at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1128)
	at java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:628)
	at java.base/java.lang.Thread.run(Thread.java:834)

```

- 笔者使用的版本(5.5.1)通过编译.

![image-20191230104523967](assets/image-20191230104523967.png)

### 编译阶段

- **这一步会消费很长的时间**. 通过执行 `gradlew` 命令进行
    - windows 使用 `gradlew.bat`
    - linux 使用 `./gradlew`
    
### 测试项目开发
- 创建一个gradle的子项目,父级项目为spring
```
plugins {
    id 'java'
}

group 'org.springframework'
version '5.1.13.BUILD-SNAPSHOT'

sourceCompatibility = 1.8

repositories {
    mavenCentral()
}

dependencies {
    testCompile group: 'junit', name: 'junit', version: '4.12'

    compile(project(":spring-core"))
    compile(project(":spring-beans"))
    compile(project(":spring-aop"))
    compile(project(":spring-context"))
}
tasks.withType(JavaCompile) {
    options.encoding = 'UTF-8'
}

tasks.withType(Javadoc) {
    options.encoding = 'UTF-8'
}
```
![image-20191230105255864](assets/image-20191230105255864.png)

选择Modules ， 点击加号进行添加
- 编码设置
![image-20191230105426390](assets/image-20191230105426390.png)

- 添加完成后编写一段代码来确认spring的引用成功.
```java
import org.springframework.beans.factory.BeanExpressionException;

public class TestMain {
    public static void main(String[] args) {
        BeanExpressionException test = new BeanExpressionException("中文测试");
        System.out.println(test.getMessage());
    }
}
```
- 控制台输出`中文测试`即为环境成功搭建.
