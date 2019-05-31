package com.xy.kafka.bean.common;

import lombok.Builder;
import lombok.Data;

/**
 * @author liBai
 * @Classname ResultBean
 * @Description 返回结果统一封装类
 * @Date 2019-05-15 09:26
 */
@Data
@Builder
public class ResultBean<T> {
    /**
     * 响应状态
     */
    private int status;
    /**
     * 响应信息
     */
    private String msg;
    /**
     * 数据条数
     */
    private Integer total;
    /**
     * 返回数据
     */
    private T data;


}
