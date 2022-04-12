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
}
