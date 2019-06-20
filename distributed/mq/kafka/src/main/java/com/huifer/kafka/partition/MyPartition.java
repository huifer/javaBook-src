package com.huifer.kafka.partition;

import java.util.List;
import java.util.Map;
import java.util.Random;
import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.PartitionInfo;

/**
 * <p>Title : MyPartition </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-06-19
 */
public class MyPartition implements Partitioner {

    private Random random = new Random();

    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes,
            Cluster cluster) {
        // 获取分区列表
        List<PartitionInfo> partitionInfos = cluster.partitionsForTopic(topic);
        int partitionNum = 0;
        if (key == null) {
            partitionNum = random.nextInt(partitionInfos.size());
        } else {
            partitionNum = Math.abs((key.hashCode()) % partitionInfos.size());

        }
        System.out.println("key ->" + key);
        System.out.println("value ->" + value);
        System.out.println("partitionNumb->" + partitionNum);
        return partitionNum;
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> configs) {

    }
}
