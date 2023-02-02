package com.eth;

import com.eth.mapper.OperatorMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class EthApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(EthApplication.class, args);
        // 数据库预热
        OperatorMapper operatorMapper = (OperatorMapper)run.getBean("operatorMapper");
        operatorMapper.selectById("");

        System.out.println("后端启动成功！！！");
    }
}
