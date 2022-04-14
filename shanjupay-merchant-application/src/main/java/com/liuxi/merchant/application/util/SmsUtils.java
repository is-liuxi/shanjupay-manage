package com.liuxi.merchant.application.util;

import com.liuxi.common.pojo.CommonErrorCode;
import com.liuxi.common.util.PhoneUtil;
import com.liuxi.merchant.api.exception.BusinessException;
import com.liuxi.merchant.application.config.SpringContextConfig;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 短信验证码操作工具类，使用 redis
 * 阿里云短信服务需要备案
 * </P>
 * @author liu xi
 * @date 2022/4/12 10:13
 */
public class SmsUtils {

    /**
     * 发送验证码
     * @param phoneNum
     * @return
     */
    public static String sendSms(String phoneNum) {
        try {
            // 手机号格式校验
            if (PhoneUtil.isMatches(phoneNum)) {
                String code = uuidRandom();
                // 设置 key 为手机号，验证码为 uuid，过期时间为 60s
                SpringContextConfig.getBean(StringRedisTemplate.class).opsForValue().set(phoneNum, code, 60, TimeUnit.SECONDS);
                return code;
            }
        } catch (Exception e) {
            e.printStackTrace();
            // 发送验证码错误
            throw new BusinessException(CommonErrorCode.E_100107);
        }
        return CommonErrorCode.E_200224.getDesc();
    }

    /**
     * 检验验证码
     * @param phoneNum
     * @param code
     * @return
     */
    public static String verify(String phoneNum, String code) {
        // 手机号格式校验
        if (!PhoneUtil.isMatches(phoneNum)) {
            return CommonErrorCode.E_200224.getDesc();
        }

        try {
            // 从缓存中拿到验证码
            String codeVal = SpringContextConfig.getBean(StringRedisTemplate.class).opsForValue().get(phoneNum);
            if (codeVal == null) {
                return CommonErrorCode.E_100103.getDesc();
            }
            if (code.equals(codeVal)) {
                // 验证成功后，删除
                SpringContextConfig.getBean(StringRedisTemplate.class).delete(phoneNum);
                return "success";
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(CommonErrorCode.UNKNOWN.getDesc());
        }
        return null;
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
