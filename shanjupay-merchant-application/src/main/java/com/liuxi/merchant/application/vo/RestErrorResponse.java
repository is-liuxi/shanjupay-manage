package com.liuxi.merchant.application.vo;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 *
 * </P>
 * @author liu xi
 * @date 2022/4/13 4:04
 */
@Data
@ApiModel(value = "RestErrorResponse", description = "错误响应参数包装")
@AllArgsConstructor
@NoArgsConstructor
public class RestErrorResponse {

    private int errCode;
    private String errMessage;
}
