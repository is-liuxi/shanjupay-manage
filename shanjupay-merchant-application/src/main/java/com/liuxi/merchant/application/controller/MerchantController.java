package com.liuxi.merchant.application.controller;

import com.aliyun.oss.OSS;
import com.liuxi.common.pojo.CommonErrorCode;
import com.liuxi.merchant.api.MerchantService;
import com.liuxi.merchant.api.dto.MerchantDto;
import com.liuxi.merchant.api.exception.BusinessException;
import com.liuxi.merchant.api.vo.ResultVo;
import com.liuxi.merchant.application.util.SecurityUtil;
import com.liuxi.merchant.application.util.SmsUtils;
import com.liuxi.merchant.application.util.UploadImageUtils;
import com.liuxi.merchant.application.vo.AliYun;
import com.liuxi.merchant.application.vo.MerchantDetailsVo;
import com.liuxi.merchant.application.vo.MerchantRegistryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.http.HttpStatus;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * <p>
 *
 * </P>
 * @author liu xi
 * @date 2022/4/11 23:05
 */
@RestController
@Api(value = "商户平台‐商户相关", tags = "商户平台‐商户相关")
@Slf4j
public class MerchantController extends AbstractResultController<MerchantDto> {

    @Reference
    private MerchantService merchantService;

    @Autowired
    private OSS oss;

    @Autowired
    private AliYun aliYun;

    @ApiOperation(value = "根据 ID 查询商户信息", notes = "商户 ID 必填")
    @GetMapping("merchant/{id}")
    public MerchantDto queryMerchantById(@PathVariable("id") Long merchantId) {

        return merchantService.queryMerchantById(merchantId);
    }

    @ApiOperation(value = "发送验证码", notes = "随机生成六位验证码")
    @GetMapping("sendSms/{phoneNum}")
    public String sendSms(@PathVariable("phoneNum") String phoneNum) {
        return SmsUtils.sendSms(phoneNum);
    }

    @ApiOperation(value = "校验验证码", notes = "校验验证码，传入 手机号、验证码参数")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phoneNum", value = "手机号", dataType = "String", required = true),
            @ApiImplicitParam(name = "code", value = "验证码", dataType = "String", required = true)
    })
    @GetMapping("verify/code")
    public String verify(@RequestParam("phoneNum") String phoneNum, @RequestParam("code") String code) {
        return SmsUtils.verify(phoneNum, code);
    }

    @ApiOperation(value = "商户注册", notes = "商户注册功能，传入手机号")
    @ApiImplicitParam(value = "商户注册信息", name = "MerchantRegistryVo", required = true, dataType = "MerchantRegistryVo", paramType = "body")
    @PostMapping("createMerchant")
    public ResultVo<MerchantDto> createMerchant(@RequestBody MerchantRegistryVo merchantVo) {
        // 校验手机号格式
        String mobileNum = merchantVo.getMobile();
        String codeVerify = SmsUtils.verify(mobileNum, merchantVo.getVerifyCode());
        // 验证码校验是否成功
        if (StringUtils.isNotEmpty(codeVerify)) {
            // 查看手机号是否存在
            if (merchantService.queryMobileNumExist(mobileNum)) {
                MerchantDto merchantDto = new MerchantDto();
                BeanUtils.copyProperties(merchantVo, merchantDto);
                return this.responseJson(200, "商户注册成功！", merchantService.createMerchant(merchantDto));
            }
            // 手机号已存在
            return this.responseJson(CommonErrorCode.E_200203.getCode(), CommonErrorCode.E_200203.getDesc(), null);
        }
        // 验证码错误
        return this.responseJson(CommonErrorCode.E_100102.getCode(), codeVerify, null);
    }

    @PostMapping("uploadImg")
    @ApiOperation(value = "图片上传", notes = "文件上转，name=file")
    public String uploadImgToOss(@RequestParam("file") MultipartFile multipartFile) {
        String filePath = UploadImageUtils.filePath(multipartFile.getOriginalFilename());
        try {
            oss.putObject(aliYun.getBucketName(), filePath, multipartFile.getInputStream());
        } catch (IOException e) {
            log.info("文件上传失败：{}", e.getMessage());
            e.printStackTrace();
            throw new BusinessException(CommonErrorCode.E_100106);
        }
        return aliYun.getPrefix() + filePath;
    }

    @PostMapping("applyMerchant")
    @ApiOperation(value = "商家资质申请", notes = "商户认证资料")
    public ResultVo<MerchantDto> applyMerchant(@RequestBody MerchantDetailsVo merchantDetailsVo) {
        // 租户 ID
        Long merchantId = SecurityUtil.getMerchantId();
        MerchantDto dto = new MerchantDto();
        BeanUtils.copyProperties(merchantDetailsVo, dto);
        dto.setId(merchantId);
        merchantService.applyMerchant(merchantId, dto);
        return this.responseJson(HttpStatus.SC_OK, "success", null);
    }
}
