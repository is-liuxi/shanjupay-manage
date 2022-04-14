package com.liuxi.merchant.application.controller;

import com.liuxi.merchant.api.AppService;
import com.liuxi.merchant.api.dto.AppDto;
import com.liuxi.merchant.api.vo.ResultVo;
import com.liuxi.merchant.application.util.SecurityUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *
 * </P>
 * @author liu xi
 * @date 2022/4/14 13:12
 */
@RestController
@Api(value = "商户平台-应用管理", tags = "商户平台-应用相关")
public class AppController extends AbstractResultController<AppDto> {

    @Reference
    private AppService appService;

    @PostMapping("create/app")
    @ApiOperation(value = "创建应用", notes = "传入应用信息")
    @ApiImplicitParam(name = "AppDto", required = true)
    public ResultVo<AppDto> createApp(@RequestBody AppDto appDto) {
        Long merchantId = SecurityUtil.getMerchantId();
        AppDto app = appService.createApp(merchantId, appDto);
        return responseJson(200, "success", app);
    }

    @ApiOperation("查询商户下的应用列表")
    @GetMapping("queryAppByMerchant/{merchantId}")
    public ResponseEntity<List<AppDto>> queryAppByMerchant(@PathVariable("merchantId") long merchantId) {
        List<AppDto> appDtoList = appService.queryAppByMerchant(merchantId);
        return ResponseEntity.ok(appDtoList);
    }

    @ApiOperation("根据应用id查询应用信息")
    @GetMapping("queryAppById/{appId}")
    public ResponseEntity<AppDto> queryAppByMerchant(@PathVariable("appId") String appId) {
        AppDto appDto = appService.queryAppById(appId);
        return ResponseEntity.ok(appDto);
    }
}
