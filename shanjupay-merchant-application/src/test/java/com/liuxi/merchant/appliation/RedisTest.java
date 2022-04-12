package com.liuxi.merchant.appliation;

import com.liuxi.merchant.application.MerchantApplicationBootstrap;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * <p>
 *
 * </P>
 * @author liu xi
 * @date 2022/4/12 10:36
 */
@SpringBootTest(classes = MerchantApplicationBootstrap.class)
@RunWith(SpringRunner.class)
public class RedisTest {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Test
    public void setOption() {
        String key = redisTemplate.opsForValue().get("aa");
        System.out.println(key);
    }
}
