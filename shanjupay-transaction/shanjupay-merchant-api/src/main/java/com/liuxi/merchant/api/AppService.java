package com.liuxi.merchant.api;

import com.liuxi.merchant.api.dto.AppDto;
import com.liuxi.merchant.api.exception.BusinessException;

import java.util.List;

/**
 * <p>
 * 应用管理相关的接口
 * </P>
 * @author liu xi
 * @date 2022/4/14 12:42
 */
public interface AppService {

    /**
     * 创建应用
     * @param merchantId 商户 ID
     * @param appDto     应用信息
     * @return 创建成功后的应用信息
     * @throws BusinessException
     */
    AppDto createApp(long merchantId, AppDto appDto) throws BusinessException;

    /**
     * 根据 商户id 查询应用列表
     * @param merchantId
     * @return
     */
    List<AppDto> queryAppByMerchant(long merchantId);

    /**
     * 根据应用id查询应用
     * @param id
     * @return
     */
    AppDto queryAppById(String id);
}
