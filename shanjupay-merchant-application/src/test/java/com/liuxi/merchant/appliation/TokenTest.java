package com.liuxi.merchant.appliation;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.liuxi.common.util.EncryptUtil;
import com.liuxi.merchant.api.MerchantService;
import com.liuxi.merchant.api.dto.MerchantDto;
import com.liuxi.merchant.application.MerchantApplicationBootstrap;
import org.apache.dubbo.config.annotation.Reference;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * <p>
 *
 * </P>
 * @author liu xi
 * @date 2022/4/14 11:36
 */
@SpringBootTest(classes = MerchantApplicationBootstrap.class)
@RunWith(SpringRunner.class)
public class TokenTest {

    @Reference
    private MerchantService merchantService;

    @Test
    public void createTokenTest() {
        Long merchantId = 1513910598998503425L;//填写用于测试的商户id
        MerchantDto merchantDTO = merchantService.queryMerchantById(merchantId);
        JSONObject token = new JSONObject();
        token.put("mobile", merchantDTO.getMobile());
        token.put("user_name", merchantDTO.getUsername());
        token.put("merchantId", merchantId);
        String jwt_token = "Bearer " +
                EncryptUtil.encodeBase64(JSON.toJSONString(token).getBytes());
        // Bearer eyJtZXJjaGFudElkIjoxNTEzOTEwNTk4OTk4NTAzNDI1LCJtb2JpbGUiOiIxMzk3NDYzOTk1MSJ9
        System.out.println(jwt_token);
    }
}
