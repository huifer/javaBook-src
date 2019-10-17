# kafka整合flume


- 官网进行下载 http://flume.apache.org/

- 下载完成后解压

  ```shell
  tar -zxvf apache-flume-1.9.0-bin.tar.gz 
  ```

  

- 配置kafka文件

  ```properties
  agent.sources = s1                                
  agent.channels = c1                                                
  agent.sinks = k1                                   
  agent.sources.s1.type=exec                     
  agent.sources.s1.command=tail -F /tmp/logs/kafka.log                    
  agent.sources.s1.channels=c1                  
  agent.channels.c1.type=memory                    
  agent.channels.c1.capacity=10000                          
  agent.channels.c1.transactionCapacity=100       
  #设置Kafka接收器                          
  agent.sinks.k1.type= org.apache.flume.sink.kafka.KafkaSink              
  #设置Kafka的broker地址和端口号                      
  agent.sinks.k1.brokerList=192.168.1.106:9092                                       
  #设置Kafka的Topic                         
  agent.sinks.k1.topic=kafkatest                                              
  #设置序列化方式                                                         
  agent.sinks.k1.serializer.class=kafka.serializer.StringEncoder                         
  agent.sinks.k1.channel=c1    
  
  ```

- 主要配置

  1. agent.sinks.k1.brokerList=192.168.1.106:9092       
  2. agent.sinks.k1.topic=kafkatest    

- 启动

  ```shell
  bin/flume-ng agent -conf-file conf/kafka.properties -c conf/ --name agent -Dflume.root.logger=DEBUG,console
  ```

  

- 向`/tmp/logs/kafka.log`写入数据

  ` echo "a" >> /tmp/logs/kafka.log `

  ![1561517795111](assets/1561517795111.png)

字符`a`十六进制为61

https://baike.baidu.com/item/ASCII/309296?fr=aladdin
