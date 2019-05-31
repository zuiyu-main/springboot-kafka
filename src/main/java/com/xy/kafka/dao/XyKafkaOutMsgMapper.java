package com.xy.kafka.dao;

import com.xy.kafka.bean.XyKafkaOutMsg;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface XyKafkaOutMsgMapper {
    int deleteByPrimaryKey(Long id);

    int insert(XyKafkaOutMsg record);

    int insertSelective(XyKafkaOutMsg record);

    XyKafkaOutMsg selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(XyKafkaOutMsg record);

    int updateByPrimaryKey(XyKafkaOutMsg record);

    int updateByFwBh(@Param("outMsg") XyKafkaOutMsg outMsg);
}