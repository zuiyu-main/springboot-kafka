//package com.xy.kafka.config;
//
//import lombok.extern.slf4j.Slf4j;
//import org.apache.kafka.clients.producer.Partitioner;
//import org.apache.kafka.common.Cluster;
//import org.apache.kafka.common.PartitionInfo;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.List;
//import java.util.Map;
//
///**
// * @author liBai
// * @Classname MyPartition
// * @Description 自定义随机分配分区
// * @Date 2019-05-14 14:14
// */
//@Configuration
//@Slf4j
//public class MyPartition implements Partitioner {
//    @Override
//    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
//        List<PartitionInfo> partitions = cluster.partitionsForTopic(topic);
//        int numPartitions = partitions.size();
//        int partitionNum = 0;
//        try {
//            partitionNum = Integer.parseInt((String) key);
//        } catch (Exception e) {
//            partitionNum = key.hashCode() ;
//        }
//        log.debug("kafkaMessage topic:{}|key:{}|value:{}", topic, key,value);
//        return Math.abs(partitionNum  % numPartitions);
//    }
//
//    @Override
//    public void close() {
//
//    }
//
//    @Override
//    public void configure(Map<String, ?> configs) {
//
//    }
//}
