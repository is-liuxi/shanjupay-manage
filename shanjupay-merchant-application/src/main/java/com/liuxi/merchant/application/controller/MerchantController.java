package com.liuxi.merchant.application.controller;

import com.liuxi.merchant.api.MerchantService;
import com.liuxi.merchant.api.dto.MerchantDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
}
