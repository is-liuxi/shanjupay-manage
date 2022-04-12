package com.liuxi.merchant.application.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 商户注册 ViewObject
 * </P>
 * @author liu xi
 * @date 2022/4/12 23:57
 */
@Data
@ApiModel(value = "MerchantRegistryVo", description = "商户注册信息")
public class MerchantRegistryVo {
    @ApiModelProperty("商户手机号")
    private String mobile;
    @ApiModelProperty("商户用户名")
    private String username;
    @ApiModelProperty("商户密码")
    private String password;
    @ApiModelProperty("验证码")
    private String verifyCode;
}
