# HBase
## 简介
## 安装
- 从[官网](https://hbase.apache.org/)获取HBase,在本文编辑阶段使用的HBase(HBase-2.2.2).
```shell script
tar -zxvf hbase-2.2.2-bin.tar.gz
cd hbase-2.2.2
vim conf/hbase-env.sh
# 在 hbase-env.sh 中修改JAVA_HOME地址
# export JAVA_HOME=/usr/java/jdk1.8.0/
vim conf/hbase-site.xml
# 在 hbase-site.xml 中修改 web 端口可供访问
<property>
    <name>hbase.master.info.port</name>
    <value>60010</value>
</property>
```
- 启动
    - `./bin/start-hbase.sh`
- 关闭
    - `./bin/stop-hbase.sh`

## HBase shell
启动 HBase shell
`./bin/hbase sheel`
### 基础命令
#### 查看版本号
```shell script
hbase(main):010:0* version
2.2.2, re6513a76c91cceda95dad7af246ac81d46fa2589, Sat Oct 19 10:10:12 UTC 2019
Took 0.0017 seconds    
```
#### 查看当前用户
```shell script
hbase(main):011:0> whoami
huifer (auth:SIMPLE)
    groups: huifer
Took 0.0445 seconds      
```
#### 创建表
`create <table>, {NAME => <family>, VERSIONS => <VERSIONS>}`
```shell script
hbase(main):014:0> create 't_stu' ,{ NAME=>'stu_name'  , VERSIONS=>1} , {NAME => 'sex' , VERSIONS=>1}
Created table t_stu
Took 2.2221 seconds                                                                                                                                                                  
=> Hbase::Table - t_stu

```
#### 查看所有表
```shell script
hbase(main):015:0> list
TABLE                                                                                                                                                                                
t1                                                                                                                                                                                   
t_stu                                                                                                                                                                                
2 row(s)
Took 0.0497 seconds  
```