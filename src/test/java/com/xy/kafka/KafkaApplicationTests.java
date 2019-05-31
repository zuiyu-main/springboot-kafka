package com.xy.kafka;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.activation.DataSource;
import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KafkaApplicationTests {
    @Resource
    private DataSource dataSource;
    @Test
    public void testConnection() throws Exception {
        System.out.println(this.dataSource);
    }

    @Test
    public void contextLoads() {
    }

}
