# 基于GIT版本管理
- Author: [HuiFer](https://github.com/huifer)

> Git是一个 “分布式版本管理工具”，简单的理解版本管理工具：大家在写东西的时候都用过 “回撤” 这个功能，但是回撤只能回撤几步，假如想要找回我三天之前的修改，光用 “回撤” 是找不回来的。而 “版本管理工具” 能记录每次的修改，只要提交到版本仓库，你就可以找到之前任何时刻的状态（文本状态）。



## 开卷必读

- 如果你还没有使用过GIT或者不知道Git是什么可以看一下这篇文章：[git 简单使用教程](<http://rogerdudler.github.io/git-guide/index.zh.html>)



## git commit 语义化提交日志

- 每次提交都需要携带`chore`(维护)、`docs`(文档)、`feat`(新功能)、`fix`(bug修复)、`refactor`(重构)、`style`(样式)、`test`(测试) 其中一个,**不可多个重复在一个commit中**!



## 语义化版本号

以 v1.5.2 为例，1.5.2 按照英文句号分割为三部分：

- 主版本号：：是你对项目做了不兼容的 API 修改，即大版本的升级。
- 次版本号：当你做了向下兼容的功能性新增。即，新增了功能，但是不影响旧有功能的使用。
- 修订号：你做了向下兼容的问题修正。即，bug fix 版本。没有新增功能，只是修复了历史遗漏 BUG。







## git分支模型

[Vincent Driessen](https://nvie.com/about/)  提出的[git 管理流程和规范](<https://nvie.com/posts/a-successful-git-branching-model/>)

![img](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9udmllLmNvbS9pbWcvZ2l0LW1vZGVsQDJ4LnBuZw?x-oss-process=image/format,png)

### 图解

图中我们可以看到分支有`master`、`hotfix`、`develop`、`release` 、`feature` 5个分支

- master
  - 该分支不可提交代码只能从其他分支合并(`dev`&`hotfix`&`release`)
  - 管理员可操作
- hotfix
  - 当我们发现`master`上存在bug时新建`hotfix`分支进行修复修复完成后合并回`master`和`develop`分支
  - 开发人员操作
- develop
  - 开发分支,主要用来合并`feature`分支，当代码量或者开发进度到达一定程度可以发布此时可以合并到`master`分支
  - 开发人员操作
- release
  - 发布版本分支
  - 管理员可操作
- feature
  - 该分支用来开发新功能，新功能完成后合并到`develop`分支
  - 开发人员操作

- tag
  - 标签用来标识版本号
  - 都可以操作



#### git flow 解决的问题

1. 新功能如何开发
2. 功能之间不会互相影响
3. 对分支的语义化描述
4. 发布版本管理
5. 线上代码bug修复



### 推荐软件

[SourceTree](<https://www.sourcetreeapp.com/>) 



### 代码审计 code review 

#### 目标

- 提高代码质量，及早发现潜在缺陷，降低修改/弥补缺陷的成本

- 促进团队内部知识共享，提高团队整体水平

- 评审过程对于评审人员来说，也是一种思路重构的过程，帮助更多的人理解系统

- 是一个传递知识的手段，可以让其它并不熟悉代码的人知道作者的意图和想法，从而可以在以后轻松维护代码

- 可以被用来确认自己的设计和实现是一个清楚和简单的

- 鼓励相互学习对方的长处和优点

- 高效迅速完成Code Review

#### Pull Request（PR）

- 通过PR的方式进行code review
- 一个PR中不可以存在多个任务
- PR发起后在1-2天内合并或者拒绝
  - 拒绝告知具体原因



#### Code Review Checklist

##### 完整性检查

1. 是否实现当前版本提出功能
2. 是否修改bug
3. 是否创建数据库、表
4. 是否存在初始化数据需要创建
5. 是否存在任何没有定义或没有引用到的变量、常数或数据类型

##### 一致性检查

- 是否符合提出功能
- 使用的格式、符号、结构是否一致(如`tab`是2个空格还是4个或者其他)建议使用 `editorconfig`文件进行管理

##### 正确性检查

- 注释正确性
- 参数正确性

##### 可修性改检查

- 代码涉及到的常量是否易于修改(如使用配置、定义为类常量、使用专门的常量类等)

##### 可预测性检查 

- 死循环
- 无穷递归

##### 健壮性检查

- 资源释放
- 异常处理

##### 结构性检查

- 循环只有一个入口
- 功能模块的辨识度

##### 可追溯检查

- 命名唯一性
- 修订历史记录

##### 可理解性检查

- 函数、类的注解
- 删除不使用的代码
- 统一缩进
- 变量的取值范围

##### 可重用检查

- 同一段代码不重复出现
- 抽象可重用函数,类

##### 安全性检查

- 硬编码用于测试的删除
- 敏感数据

##### 性能检查

- 选择合适的数据类型
- 懒加载
- 异步
- 并行
- 缓存



#### 推荐插件

- [Alibaba Java Coding Guidelines ](https://plugins.jetbrains.com/plugin/10046-alibaba-java-coding-guidelines)
- [SonarLint](https://www.sonarlint.org/intellij/)



