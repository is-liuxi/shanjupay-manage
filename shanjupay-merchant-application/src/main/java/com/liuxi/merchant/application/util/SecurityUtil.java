package com.liuxi.merchant.application.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.liuxi.common.util.EncryptUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * <p>
 * 获取当前登录用户信息
 * 前端配置token，后续每次请求并通过Header方式发送至后端
 * </P>
 * @author liu xi
 * @date 2022/4/14 11:14
 */
public class SecurityUtil {

    /**
     * 自定义生成 token，测试使用
     * @return
     */
    public static Long getMerchantId() {
        HttpServletRequest request = ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes()))
                .getRequest();
        String jsonToken = request.getHeader("authorization");
        if (StringUtils.isEmpty(jsonToken) || !jsonToken.startsWith("Bearer ")) {
            throw new RuntimeException("token is not as expected");
        }
        jsonToken = jsonToken.substring(7);
        jsonToken = EncryptUtil.decodeUTF8StringBase64(jsonToken);
        JSONObject jsonObject = JSON.parseObject(jsonToken);
        return jsonObject.getLong("merchantId");
    }

    public static LoginUser getUser() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();

        if (servletRequestAttributes != null) {
            HttpServletRequest request = servletRequestAttributes.getRequest();

            Object jwt = request.getAttribute("jsonToken");
            if (jwt instanceof LoginUser) {
                return (LoginUser) jwt;
            }
        }
        return new LoginUser();
    }

   /* public static Long getMerchantId() {
        MerchantService merchantService = SpringContextConfig.getBean(MerchantService.class);
        MerchantDto merchant = merchantService.queryMerchantById(getUser().getTenantId());
        Long merchantId = null;
        if (merchant != null) {
            merchantId = merchant.getId();
        }
        return merchantId;
    }*/

    /**
     * 转换明文jsonToken为用户对象
     * @param token
     * @return
     */
    public static LoginUser convertTokenToLoginUser(String token) {
        token = EncryptUtil.decodeUTF8StringBase64(token);
        LoginUser user = new LoginUser();
        JSONObject jsonObject = JSON.parseObject(token);
        String payload = jsonObject.getString("payload");
        Map<String, Object> payloadMap = JSON.parseObject(payload, Map.class);
        user.setPayload(payloadMap);
        user.setClientId(jsonObject.getString("client_id"));
        user.setMobile(jsonObject.getString("mobile"));
        user.setUsername(jsonObject.getString("user_name"));
        return user;
    }
}
