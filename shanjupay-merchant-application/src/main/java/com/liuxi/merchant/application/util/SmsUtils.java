package com.liuxi.merchant.application.util;

import com.liuxi.merchant.application.config.SpringContextConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 短信操作工具类，使用 redis
 * 阿里云短信服务需要备案
 * </P>
 * @author liu xi
 * @date 2022/4/12 10:13
 */
public class SmsUtils {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 发送短信
     * @param phoneNum
     * @return
     */
    public static String sendSms(String phoneNum) {
        String code = uuidRandom();
        // 设置 key 为手机号，验证码为 uuid，过期时间为 60s
        SpringContextConfig.getBean(StringRedisTemplate.class).opsForValue().set(phoneNum, code, 60, TimeUnit.SECONDS);
        return code;
    }

    /**
     * 检验验证码
     * @param phone
     * @param code
     * @return
     */
    public static String verify(String phone, String code) {
        String codeVal = SpringContextConfig.getBean(StringRedisTemplate.class).opsForValue().get(phone);
        if (codeVal == null) {
            return "验证码已过期，请重新发送！";
        }
        if (code.equals(codeVal)) {
            // 验证成功后，删除
            SpringContextConfig.getBean(StringRedisTemplate.class).delete(phone);
            return "success";
        }
        return "code fail";
    }

    /**
     * 生成六位的随机数
     * @return
     */
    private static String uuidRandom() {
        String uuid = UUID.randomUUID().toString();
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < uuid.length(); i++) {
            if (i == 6) {
                break;
            }
            code.append(uuid.charAt(i));
        }
        return code.toString();
    }
}
