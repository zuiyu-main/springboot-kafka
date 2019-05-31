package com.xy.kafka;

import com.xy.kafka.bean.common.ResultBean;
import com.xy.kafka.thread.ConsumerMsgThread;
import javafx.application.Application;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * @author liBai
 * @Classname ConsumerThreadTest
 * @Description 多线程消费消息测试
 * @Date 2019-05-15 09:32
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class ConsumerThreadTest {

    @Test
    public void getMsgTest() throws Exception {
//        ConsumerMsgThread msgThread = new ConsumerMsgThread();
//        Thread thread1 = new Thread(msgThread);
//        Thread thread2 = new Thread(msgThread);
//        thread1.start();
//        thread2.start();
    }
}
