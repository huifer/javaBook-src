#!/bin/bash
echo "Restarting SpringBoot Application"
pid=`ps -ef | grep jenkins-spring-boot-0.0.1-SNAPSHOT.jar | grep -v grep | awk '{print $2}'`
if [ -n "$pid" ]
then
   kill -9 $pid
   echo "关闭进程："$pid
fi

echo "授予当前用户权限"
echo "执行....."
nohup  java -jar /var/lib/jenkins/workspace/jenkins-spring-boot/target/jenkins-spring-boot-0.0.1-SNAPSHOT.jar --spring.config.location=/home/huifer/jenkins-spring-boot.yml > device-temp.txt 2>&1 &

echo "启动成功"