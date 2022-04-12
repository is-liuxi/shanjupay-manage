package com.liuxi.merchant.service;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * <p>
 *
 * </P>
 * @author liu xi
 * @date 2022/4/11 22:14
 */
@SpringBootApplication
public class MerchantServiceBootstrap {
    public static void main(String[] args) {

        SpringApplication.run(MerchantServiceBootstrap.class, args);
    }
}
