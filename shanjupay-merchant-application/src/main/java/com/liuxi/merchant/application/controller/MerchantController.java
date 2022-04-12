package com.liuxi.merchant.application.controller;

import com.liuxi.common.pojo.CommonErrorCode;
import com.liuxi.common.util.PhoneUtil;
import com.liuxi.merchant.api.MerchantService;
import com.liuxi.merchant.api.dto.MerchantDto;
import com.liuxi.merchant.application.util.SmsUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *
 * </P>
 * @author liu xi
 * @date 2022/4/11 23:05
 */
@RestController
@Api(value = "商户平台‐商户相关", tags = "商户平台‐商户相关")
public class MerchantController {

    @Reference
    private MerchantService merchantService;

    @ApiOperation(value = "根据 ID 查询商户信息", notes = "商户 ID 必填")
    @GetMapping("merchant/{id}")
    public MerchantDto queryMerchantById(@PathVariable("id") Long merchantId) {

        return merchantService.queryMerchantById(merchantId);
    }

    @ApiOperation(value = "发送验证码", notes = "随机生成六位验证码")
    @GetMapping("sendSms/{phoneNum}")
    public String sendSms(@PathVariable("phoneNum") String phoneNum) {
        // 校验手机号格式
        if (PhoneUtil.isMatches(phoneNum)) {
            return SmsUtils.sendSms(phoneNum);
        }
        // 手机号码格式不正确
        return CommonErrorCode.E_200224.getDesc();
    }

    @ApiOperation(value = "校验验证码", notes = "校验验证码，传入 手机号、验证码参数")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phoneNum", value = "手机号", dataType = "String", required = true),
            @ApiImplicitParam(name = "code", value = "验证码", dataType = "String", required = true)
    })
    @GetMapping("verify/code")
    public String verify(@RequestParam("phoneNum") String phoneNum, @RequestParam("code") String code) {
        // 校验手机号格式
        if (PhoneUtil.isMatches(phoneNum)) {
            return SmsUtils.verify(phoneNum, code);
        }
        // 手机号码格式不正确
        return CommonErrorCode.E_200224.getDesc();
    }
}
