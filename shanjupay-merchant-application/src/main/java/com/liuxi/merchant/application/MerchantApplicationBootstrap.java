package com.liuxi.merchant.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <p>
 * 商户平台应用引导类
 * </P>
 * @author liu xi
 * @date 2022/4/11 21:32
 */
@SpringBootApplication
public class MerchantApplicationBootstrap {
    public static void main(String[] args) {

        SpringApplication.run(MerchantApplicationBootstrap.class, args);
    }
}
