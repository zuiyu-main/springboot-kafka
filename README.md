# springboot-kafka

  记录一次springboot集成kafka的过程

* 新版本springboot+kafka请参考[springboot+kafka新版本](https://github.com/TianPuJun/springboot-demo)

## 创建数据库表
* sql 生产消息一致表
```
CREATE TABLE `xy_kafka_in_msg` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `fw_bh` bigint(20) NOT NULL COMMENT '档案号',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_update` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `fwbh` (`fw_bh`) USING BTREE COMMENT '服务唯一标示'
) ENGINE=InnoDB AUTO_INCREMENT=372 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='消息消费成功表 幂等性';
```
* sql 消费消息一致表
```
CREATE TABLE `xy_kafka_out_msg` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `fw_bh` bigint(20) NOT NULL COMMENT '档案号',
  `deal_flag` tinyint(2) NOT NULL DEFAULT '0' COMMENT '处理标记 0 失败 1 成功',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_update` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `fwbh` (`fw_bh`) USING BTREE COMMENT '服务唯一标示'
) ENGINE=InnoDB AUTO_INCREMENT=715 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='任务添加进kafka队列 幂等性';
```

## 配置连接mysql

* mysql数据库连接设置

```
 spring:
   datasource:
     type: com.alibaba.druid.pool.DruidDataSource    # 配置当前要使用的数据源的操作类型
     driver-class-name: com.mysql.cj.jdbc.Driver      # 配置MySQL的驱动程序类
     url: jdbc:mysql://192.168.1.200:3306/kafka?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8          # 数据库连接地址
     username: kafka                                  # 数据库用户名
     password: 123456                            # 数据库连接密码
```
* 配置kafka服务器生产者，消费者信息

```
kafka:
    # 以逗号分隔的地址列表，用于建立与Kafka集群的初始连接(kafka 默认的端口号为9092)
    bootstrap-servers: 192.168.1.200:9092
```
      
## 启动步骤
 * 1.创建mysql数据表
 * 2.修改数据库连接信息，kafka连接信息
 * 3.启动 KafkaApplication

