package com.liuxi.merchant.api;

import com.liuxi.merchant.api.dto.MerchantDto;

/**
 * <p>
 * DTO类型的对象作为service层传输的对象
 * </P>
 * @author liu xi
 * @date 2022/4/11 22:55
 */
public interface MerchantService {

    /**
     * 根据ID查询详细信息
     * @param merchantId
     * @return
     */
    MerchantDto queryMerchantById(Long merchantId);

    /**
     * 商户注册功能，接受账号、密码、手机号
     * @param merchantDto 注册商户信息
     * @return 注册成功的商户信息
     */
    MerchantDto createMerchant(MerchantDto merchantDto);

    /**
     * 查询手机号是否存在
     * @param phoneNum
     * @return
     */
    boolean queryMobileNumExist(String phoneNum);
}
