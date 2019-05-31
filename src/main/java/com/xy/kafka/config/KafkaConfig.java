package com.xy.kafka.config;

import com.xy.kafka.constant.Topic;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author liBai
 * @Classname KafkaConfig
 * @Description 多消费者组配置
 * @Date 2019-05-14 10:31
 */
@Configuration
public class KafkaConfig {
    @Bean
    public NewTopic groupTopic() {
        // 指定主题名称，分区数量，和复制因子
        return new NewTopic(Topic.GROUP, 1, (short) 2);
    }

}
