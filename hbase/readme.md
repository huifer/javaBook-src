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
- 资源来自: https://www.tutorialspoint.com/hbase/hbase_create_data.htm
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
#### 表结构
`desc <table>`
```shell script
hbase(main):003:0> desc 't_stu'
Table t_stu is ENABLED                                                                                                                                                                
t_stu                                                                                                                                                                                 
COLUMN FAMILIES DESCRIPTION                                                                                                                                                           
{NAME => 'sex', VERSIONS => '1', EVICT_BLOCKS_ON_CLOSE => 'false', NEW_VERSION_BEHAVIOR => 'false', KEEP_DELETED_CELLS => 'FALSE', CACHE_DATA_ON_WRITE => 'false', DATA_BLOCK_ENCODING
 => 'NONE', TTL => 'FOREVER', MIN_VERSIONS => '0', REPLICATION_SCOPE => '0', BLOOMFILTER => 'ROW', CACHE_INDEX_ON_WRITE => 'false', IN_MEMORY => 'false', CACHE_BLOOMS_ON_WRITE => 'fa
lse', PREFETCH_BLOCKS_ON_OPEN => 'false', COMPRESSION => 'NONE', BLOCKCACHE => 'true', BLOCKSIZE => '65536'}                                                                          

{NAME => 'stu_name', VERSIONS => '1', EVICT_BLOCKS_ON_CLOSE => 'false', NEW_VERSION_BEHAVIOR => 'false', KEEP_DELETED_CELLS => 'FALSE', CACHE_DATA_ON_WRITE => 'false', DATA_BLOCK_ENC
ODING => 'NONE', TTL => 'FOREVER', MIN_VERSIONS => '0', REPLICATION_SCOPE => '0', BLOOMFILTER => 'ROW', CACHE_INDEX_ON_WRITE => 'false', IN_MEMORY => 'false', CACHE_BLOOMS_ON_WRITE =
> 'false', PREFETCH_BLOCKS_ON_OPEN => 'false', COMPRESSION => 'NONE', BLOCKCACHE => 'true', BLOCKSIZE => '65536'}                                                                     

2 row(s)

QUOTAS                                                                                                                                                                                
0 row(s)
Took 0.5293 seconds     
```
#### 删除表
分两步走
1. `disable <table>`
1. `drop <table>` 
```shell script
hbase(main):005:0> disable 't_stu'
Took 1.3800 seconds      
hbase(main):006:0> drop 't_stu'
Took 0.4954 seconds     
hbase(main):007:0> list
TABLE                                                                                    
t1                                                                                       
1 row(s)
Took 0.0080 seconds                                                                      
=> ["t1"]

```
#### 启用表
`enable <table>`
```shell script
hbase(main):022:0> enable 't_stu'
Took 0.0196 seconds   
```

#### 判断表是否存在
`exists <table>`
```shell script
hbase(main):007:0> exists 't_stu'
Table t_stu does exist                                                                                           
Took 0.0203 seconds                                                                                              
=> true

hbase(main):008:0> exists 't_stu1'
Table t_stu1 does not exist                                                                                      
Took 0.0111 seconds                                                                                              
=> false

```


#### 插入数据
`put ’<table name>’,’row1’,’<colfamily:colname>’,’<value>’`
```shell script
hbase(main):001:0> put 't_stu' ,'1','stu_name','zz'
Took 1.0299 seconds   
```
#### 查看数据
`get ’<table name>’,’row1’`
```shell script
hbase(main):003:0> get 't_stu',1
COLUMN                        CELL                                                                               
 sex:                         timestamp=1574729126817, value=man                                                 
 stu_name:                    timestamp=1574729249797, value=zz                                                  
1 row(s)
Took 0.1011 seconds                                                                                              
hbase(main):004:0> get 't_stu',2
COLUMN                        CELL                                                                               
0 row(s)
Took 0.0056 seconds   
```
#### 清空表
`truncate <table>`
```shell script
hbase(main):005:0> truncate 't_stu'
Truncating 't_stu' table (it may take a while):
Disabling table...
Truncating table...
Took 2.7553 seconds        
hbase(main):006:0> get 't_stu',1
COLUMN                        CELL                                                                               
0 row(s)
Took 0.6695 seconds     
```
#### 修改值
```shell script
hbase(main):014:0> put 't_stu' ,'1','stu_name','zz'
Took 0.0072 seconds                                                                                              
hbase(main):015:0> get 't_stu',1
COLUMN                        CELL                                                                               
 stu_name:                    timestamp=1574731668630, value=zz                                                  
1 row(s)
Took 0.0158 seconds                                                                                              
hbase(main):016:0> put 't_stu' ,'1','stu_name','zz123'
Took 0.0081 seconds                                                                                              
hbase(main):017:0> get 't_stu',1
COLUMN                        CELL                                                                               
 stu_name:                    timestamp=1574731686590, value=zz123                                               
1 row(s)
Took 0.0110 seconds     
```


## JAVA API
