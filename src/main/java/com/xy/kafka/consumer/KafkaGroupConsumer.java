//package com.xy.kafka.consumer;
//
//import com.xy.kafka.constant.Topic;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.kafka.clients.consumer.ConsumerRecord;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.kafka.annotation.TopicPartition;
//import org.springframework.stereotype.Component;
//
///**
// * @author liBai
// * @Classname KafkaGroupConsumer
// * @Description 消费者组消费
// * @Date 2019-05-14 10:33
// */
//@Slf4j
//@Component
//public class KafkaGroupConsumer {
//
//    /**
//     * 分组1 中的消费者1
//     */
//    @KafkaListener(id = "consumer1-1", groupId = "group1", topicPartitions =
//            {@TopicPartition(topic = Topic.GROUP, partitions = {"0", "1"})
//            })
//    public void consumer1_1(ConsumerRecord<String, Object> record) {
//        log.info("consumer1-1 收到消息,content={}",record.value());
//    }
//
//    /**
//     * 分组1 中的消费者2
//     * @param record
//     */
//    @KafkaListener(id = "consumer1-2", groupId = "group2", topicPartitions =
//            {@TopicPartition(topic = Topic.GROUP, partitions = {"2", "3"})
//            })
//    public void consumer1_2(ConsumerRecord<String, Object> record) {
//        log.info("consumer1-2 收到消息,content={}",record.value());
//    }
//
////    /**
////     *  分组1 中的消费者3
////     * @param record
////     */
////    @KafkaListener(id = "consumer1-3", groupId = "group1", topicPartitions =
////            {@TopicPartition(topic = Topic.GROUP, partitions = {"0", "1"})
////            })
////    public void consumer1_3(ConsumerRecord<String, Object> record) {
////        log.info("consumer1-3 收到消息,content={}",record.value());
////    }
////
////    /**
////     * 分组2 中的消费者
////     * @param record
////     */
////    @KafkaListener(id = "consumer2-1", groupId = "group2", topics =Topic.GROUP)
////    public void consumer2_1(ConsumerRecord<String, Object> record) {
////        log.info("consumer2-1 收到消息,content={}",record.value());
////    }
//
//}
