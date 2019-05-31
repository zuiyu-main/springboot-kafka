package com.xy.kafka.dao;

import com.xy.kafka.bean.XyKafkaInMsg;
import org.springframework.stereotype.Repository;

@Repository
public interface XyKafkaInMsgMapper {
    int deleteByPrimaryKey(Long id);

    int insert(XyKafkaInMsg record);

    int insertSelective(XyKafkaInMsg record);

    XyKafkaInMsg selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(XyKafkaInMsg record);

    int updateByPrimaryKey(XyKafkaInMsg record);

    XyKafkaInMsg selectByFuBh(Long fwBh);
}