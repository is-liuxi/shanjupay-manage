package com.liuxi.merchant.service.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
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

    @Override
    public MerchantDto createMerchant(MerchantDto merchantDto) {
        Merchant merchant = new Merchant();
        // 手机号
        merchant.setMobile(merchantDto.getMobile());
        // 设置审核状态，0：未申请
        merchant.setAuditStatus("0");
        merchantMapper.insert(merchant);

        // 返回自增 ID
        merchantDto.setId(merchant.getId());
        return merchantDto;
    }

    @Override
    public boolean queryMobileNumExist(String phoneNum) {
        LambdaQueryWrapper<Merchant> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Merchant::getMobile, phoneNum);
        return merchantMapper.selectOne(wrapper) == null;
    }

    @Override
    public void applyMerchant(long merchantId, MerchantDto merchantDto) {
        Merchant merchantData = merchantMapper.selectById(merchantDto.getId());
        if (merchantData == null) {
            throw new RuntimeException("账号不存在");
        }

        Merchant merchant = new Merchant();
        BeanUtils.copyProperties(merchantDto, merchant);
        // 1-已申请待审核
        merchant.setAuditStatus("1");
        // 从数据库中拿，申请时不能修改这些值
        merchant.setId(merchantData.getId());
        merchant.setMobile(merchantData.getMobile());
        merchant.setTenantId(merchantData.getTenantId());
        merchantMapper.updateById(merchant);
    }
}
