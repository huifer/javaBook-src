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