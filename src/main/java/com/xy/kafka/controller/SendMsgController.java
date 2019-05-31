package com.xy.kafka.controller;

import com.xy.kafka.constant.Topic;
import com.xy.kafka.producer.KafKaConsumerProducer;
import com.xy.kafka.service.SendMsgService;
import com.xy.kafka.thread.ConsumerMsgThread;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.StringUtils;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.*;

/**
 * @author liBai
 * @Classname SendMsgController
 * @Description 发送消息接口类
 * @Date 2019-05-14 10:11
 */
@Slf4j
@RestController
public class SendMsgController {
    @Autowired
    private KafKaConsumerProducer producer;
    @Autowired
    private KafkaTemplate kafkaTemplate;
    @Autowired
    private SendMsgService sendMsgService;
    @Autowired
    private ConsumerMsgThread consumerMsgThread1;
    private ConsumerMsgThread consumerMsgThread2;
    /***
     * 发送消息体为基本类型的消息
     */
    @GetMapping("/sendSimple")
    public String sendSimple() {
        producer.sendMessage(Topic.SIMPLE, "hello spring boot kafka");
        return "success";
    }

    /***
     * 多消费者组、组中多消费者对同一主题的消费情况
     */
    @GetMapping("/sendGroup")
    public String sendGroup() {
        for (int i = 0; i < 4; i++) {
            // 第二个参数指定分区，第三个参数指定消息键 分区优先
            int i2 = i % 4 ;
            log.info("partition = {}",i2);
            ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send(Topic.GROUP, i % 4, "key", "hello group " + i);
            future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
                @Override
                public void onFailure(Throwable throwable) {
                    log.info("发送消息失败,{}" + throwable.getMessage());
                }

                @Override
                public void onSuccess(SendResult<String, Object> sendResult) {
                    log.info("发送消息成功,{}", sendResult.toString());
                }
            });

        }
        return "group success";
    }

    /**
     * 获取消息
     * @return
     */
    @GetMapping("/getMsg")
    public String getMsg(){
        long startTime = System.currentTimeMillis();
        String msg = sendMsgService.getMsg2();
        long endTime = System.currentTimeMillis();
        log.info("获取任务时间：{}ms",endTime-startTime);
        return msg;
    }

    /**
     * 处理完成业务回传业务唯一标识修改状态
     * @param fuBh 全局唯一服务标识
     */
    @GetMapping("/resolve")
    public String resolve(@RequestParam("fuBh") Long fuBh){
        sendMsgService.resolve(fuBh);
        return "success";
    }

    @GetMapping("/getMsgThread")
    public String getMsgThread() throws ExecutionException, InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(10);
        StringBuilder builder = new StringBuilder(10);
        for (int i = 0;i<=10;i++){
            ConsumerMsgThread thread1 = new ConsumerMsgThread(sendMsgService);
            Future future = pool.submit(thread1);
            String msg = future.get()==null?"abc":future.get().toString();
            builder.append(msg).append(";");
        }
        pool.shutdown();
        return builder.toString();

//        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 200, TimeUnit.MILLISECONDS,
//                new ArrayBlockingQueue<Runnable>(5));
//
//        for(int i=0;i<15;i++){
//            executor.execute(consumerMsgThread);
//            System.out.println("线程池中线程数目："+executor.getPoolSize()+"，队列中等待执行的任务数目："+
//                    executor.getQueue().size()+"，已执行玩别的任务数目："+executor.getCompletedTaskCount());
//        }
//        executor.shutdown();

        //return "success";
    }
}
