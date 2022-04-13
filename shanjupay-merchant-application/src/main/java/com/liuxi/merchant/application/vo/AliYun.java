package com.liuxi.merchant.application.vo;

import lombok.Data;
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
    private String accessKeyId;
    private String accessKeySecret;
    private String bucketName;
    private String prefix;
}
