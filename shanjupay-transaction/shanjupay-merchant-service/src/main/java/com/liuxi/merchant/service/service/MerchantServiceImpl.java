package com.liuxi.merchant.service.service;

import com.liuxi.merchant.api.MerchantService;
import com.liuxi.merchant.api.dto.MerchantDto;
import com.liuxi.merchant.service.mapper.MerchantMapper;
import com.liuxi.merchant.service.pojo.Merchant;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 *
 * </P>
 * @author liu xi
 * @date 2022/4/11 23:00
 */
@Service
public class MerchantServiceImpl implements MerchantService {

    @Autowired
    private MerchantMapper merchantMapper;

    @Override
    public MerchantDto queryMerchantById(Long merchantId) {
        Merchant merchant = merchantMapper.selectById(merchantId);
        MerchantDto merchantDto = new MerchantDto();
        BeanUtils.copyProperties(merchant, merchantDto);
        return merchantDto;
    }
}
