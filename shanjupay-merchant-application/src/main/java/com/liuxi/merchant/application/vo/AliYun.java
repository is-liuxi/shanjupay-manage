package com.liuxi.merchant.application.vo;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * <p>
 *
 * </P>
 * @author liu xi
 * @date 2022/4/13 6:24
 */
@PropertySource("classpath:aliyun-config.properties")
@ConfigurationProperties(prefix = "aliyun")
@Component
@Data
public class AliYun {

    private String endpoint;
    /**
     * nacos 中配置
     */
    @Value("${aliyun.accessKeyId}")
    private String accessKeyId;
    @Value("${aliyun.accessKeySecret}")
    private String accessKeySecret;
    private String bucketName;
    private String prefix;
}
