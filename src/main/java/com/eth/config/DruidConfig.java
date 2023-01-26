package com.eth.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DruidConfig {

    // 若持久层如果使用的是Jpa，在druidDataSource方法上添加@Bean("duridDatasource")；
    // 若持久层使用的是mybatis，在druidDataSource方法上添加@Bean(destroyMethod = "close", initMethod = "init")；
    // 因为：mybatis在不使用springboot整合的时候 也是需要配置释放方法以及初始化方法
    @ConfigurationProperties(prefix = "spring.datasource.druid")
    @Bean()
    public DataSource druid() {
        return new DruidDataSource();
    }
}