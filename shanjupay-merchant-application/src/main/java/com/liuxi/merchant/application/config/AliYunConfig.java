package com.liuxi.merchant.application.config;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.liuxi.merchant.application.vo.AliYun;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 *
 * </P>
 * @author liu xi
 * @date 2022/4/13 6:23
 */
@Configuration
public class AliYunConfig {

    @Bean
    public OSS ossClient(@Autowired AliYun aliYun) {

        return new OSSClientBuilder().build(aliYun.getEndpoint(), aliYun.getAccessKeyId(), aliYun.getAccessKeySecret());
    }
}
