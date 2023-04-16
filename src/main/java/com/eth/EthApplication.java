package com.eth;

import com.eth.mapper.ConfigMapper;
import com.eth.pojo.ConfigPo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class EthApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(EthApplication.class, args);
        System.out.println("后端启动成功！！！");
    }
}
