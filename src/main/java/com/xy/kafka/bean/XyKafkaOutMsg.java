package com.xy.kafka.bean;

import com.sun.javafx.beans.IDProperty;
import lombok.Builder;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
public class XyKafkaOutMsg {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private Long fwBh;

    private Byte dealFlag;

    private Date gmtCreate;

    private Date gmtUpdate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFwBh() {
        return fwBh;
    }

    public void setFwBh(Long fwBh) {
        this.fwBh = fwBh;
    }

    public Byte getDealFlag() {
        return dealFlag;
    }

    public void setDealFlag(Byte dealFlag) {
        this.dealFlag = dealFlag;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtUpdate() {
        return gmtUpdate;
    }

    public void setGmtUpdate(Date gmtUpdate) {
        this.gmtUpdate = gmtUpdate;
    }
}