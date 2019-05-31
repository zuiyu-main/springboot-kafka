package com.xy.kafka.service.impl;

import com.xy.kafka.bean.XyKafkaOutMsg;
import com.xy.kafka.constant.RunStatus;
import com.xy.kafka.constant.Topic;
import com.xy.kafka.dao.XyKafkaOutMsgMapper;
import com.xy.kafka.service.SendMsgService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.Duration;
import java.util.*;

/**
 * @author liBai
 * @Classname SendMsgServiceImpl
 * @Description 队列读取消息
 * @Date 2019-05-14 15:03
 */
@Slf4j
@Service
public class SendMsgServiceImpl implements SendMsgService {
    @Autowired
    private XyKafkaOutMsgMapper xyKafkaOutMsgMapper;
    /**
     * 初始化消息队列，发送之后暂时保存在list中，然后最早头部读取 earliest
     */
    private static final LinkedList<Long> linkedList = new LinkedList<>();
    private static String content = "";

    @KafkaListener(groupId = "simpleGroup", topics = Topic.SIMPLE)
    public void consumer1_1(ConsumerRecord<String, Object> record, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic, Consumer consumer, Acknowledgment ack) {
        log.info("单独消费者消费消息,topic= {} ,content = {}",topic,record.value());
        log.info("consumer content = {}",consumer);
        Optional<Object> kafkaMessage = Optional.ofNullable(record.value());
        if (kafkaMessage.isPresent()) {
        Object message = kafkaMessage.get();
        XyKafkaOutMsg build = new XyKafkaOutMsg();
        build.setGmtCreate(new Date());
            // TODO: 2019-05-15 此处fwBh就是消息队列的消息，后期使用修改
        build.setFwBh(System.currentTimeMillis());
        int insertOutMsg = xyKafkaOutMsgMapper.insertSelective(build);
        log.info("insertOutMsg result,{}",insertOutMsg==1 ? "成功" : "失败");
        linkedList.add(build.getId());
        ack.acknowledge(); // 手动提交消费消息
    }
        /*
         * 如果需要手工提交异步 consumer.commitSync();
         * 手工同步提交 consumer.commitAsync()
         */
    }


    @Override
    public String getMsg() {
        Properties props = new Properties();
        props.put("bootstrap.servers", "192.168.1.114:9092");
        props.put("group.id", "group1");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        //latest 最新  earliest 最早
        props.put("auto.offset.reset", "earliest");
        props.put("session.timeout.ms", "30000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<String, String>(props);
        //指定订阅的topic
        kafkaConsumer.subscribe(Arrays.asList(Topic.SIMPLE));
        String content = "";
//        while (true) {
            log.info("nothing available...");
            ConsumerRecords<String, String> records = kafkaConsumer.poll(Duration.ofSeconds(1000));
            for (ConsumerRecord<String, String> record : records) {
                log.info("获取消息详情，{}", record.value());
                content = record.value();
                if (content!=""){
                    XyKafkaOutMsg build = new XyKafkaOutMsg();
                    build.setGmtCreate(new Date());
                    build.setFwBh(123L);
                    int insertOutMsg = xyKafkaOutMsgMapper.insertSelective(build);
                    log.info("insertOutMsg result,{}",insertOutMsg==1 ? "成功" : "失败");
                    return content;
                }
            }
//        }
        return content;
    }
    @Override
    public synchronized String  getMsg2(){
        log.info("队列消息list，{}",linkedList);
        while (true){
            if (CollectionUtils.isEmpty(linkedList)){
                log.info("任务队列为空");
                return null;
            }
            XyKafkaOutMsg outMsg = xyKafkaOutMsgMapper.selectByPrimaryKey(linkedList.getFirst());
            if (outMsg==null){
                log.info("获取档案对象失败");
                //移除队列中数据
                log.info("队列移除的元素={}",linkedList.getFirst());
                linkedList.remove(linkedList.getFirst());
            }else if (RunStatus.SAY_NO.equals(outMsg.getDealFlag())){
                log.info("队列获取消息，总数={}，本条消息档案号={}",linkedList.size(),outMsg.getFwBh().toString());
                log.info("队列移除的元素={}",linkedList.getFirst());
                linkedList.remove(linkedList.getFirst());
                return outMsg.getFwBh().toString();
            }else if (RunStatus.SAY_YES.equals(outMsg.getDealFlag())){
                log.info("已经消费过");
                log.info("队列移除的元素={}",linkedList.getFirst());
                linkedList.remove(linkedList.getFirst());
            }
        }

    }



    @Override
    public void resolve(Long fuBh) {
        XyKafkaOutMsg outMsg =new  XyKafkaOutMsg();
        outMsg.setFwBh(fuBh);
        outMsg.setDealFlag(new Byte("1"));
        int updateStatus = xyKafkaOutMsgMapper.updateByFwBh(outMsg);
        log.info("insertOutMsg result,{}",updateStatus==1 ? "成功" : "失败");
    }
}
