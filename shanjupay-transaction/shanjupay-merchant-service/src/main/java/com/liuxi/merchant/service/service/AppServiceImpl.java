package com.liuxi.merchant.service.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.liuxi.common.pojo.CommonErrorCode;
import com.liuxi.merchant.api.AppService;
import com.liuxi.merchant.api.dto.AppDto;
import com.liuxi.merchant.api.exception.BusinessException;
import com.liuxi.merchant.service.mapper.AppMapper;
import com.liuxi.merchant.service.mapper.MerchantMapper;
import com.liuxi.merchant.service.pojo.App;
import com.liuxi.merchant.service.pojo.Merchant;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 *
 * </P>
 * @author liu xi
 * @date 2022/4/14 12:46
 */
@Service
public class AppServiceImpl implements AppService {

    @Autowired
    private AppMapper appMapper;

    @Autowired
    private MerchantMapper merchantMapper;

    @Override
    public AppDto createApp(long merchantId, AppDto appDto) throws BusinessException {
        // 查看商户是否已经通过审核
        Merchant merchant = merchantMapper.selectById(merchantId);
        if (merchant == null) {
            // 商户不存在
            throw new BusinessException(CommonErrorCode.E_200227);
        }
        if (!"2".equals(merchant.getAuditStatus())) {
            // 资质审核未通过
            throw new BusinessException(CommonErrorCode.E_200236);
        }
        if (appNameExist(appDto.getAppName())) {
            // 商户应用名称已存在
            throw new BusinessException(CommonErrorCode.E_200137);
        }
        // 应用 ID
        String appId = UUID.randomUUID().toString();
        appDto.setAppId(appId);
        appDto.setMerchantId(merchantId);
        App app = new App();
        BeanUtils.copyProperties(appDto, app);
        appMapper.insert(app);
        return appDto;
    }

    /**
     * 查询应用名称是否已经存在
     * @param appName
     * @return
     */
    private boolean appNameExist(String appName) {
        LambdaQueryWrapper<App> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(App::getAppName, appName);
        int count = appMapper.selectCount(queryWrapper);
        return count > 0;
    }

    @Override
    public List<AppDto> queryAppByMerchant(long merchantId) {
        LambdaQueryWrapper<App> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(App::getMerchantId, merchantId);
        List<App> appList = appMapper.selectList(queryWrapper);
        List<AppDto> appDtoArrayList = new ArrayList<>();
        appList.forEach(item -> {
            AppDto appDto = new AppDto();
            BeanUtils.copyProperties(item, appDto);
            appDtoArrayList.add(appDto);
        });
        return appDtoArrayList;
    }

    @Override
    public AppDto queryAppById(String id) {
        LambdaQueryWrapper<App> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(App::getAppId, id);
        App app = appMapper.selectOne(queryWrapper);
        AppDto appDto = new AppDto();
        BeanUtils.copyProperties(app, appDto);
        return appDto;
    }
}
