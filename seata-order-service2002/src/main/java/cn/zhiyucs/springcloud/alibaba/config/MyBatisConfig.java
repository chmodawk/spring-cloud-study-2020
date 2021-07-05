package cn.zhiyucs.springcloud.alibaba.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan({"cn.zhiyucs.springcloud.alibaba.dao"})
public class MyBatisConfig {
}
