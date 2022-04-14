package com.liuxi.merchant.application.exception;

import com.liuxi.common.pojo.CommonErrorCode;
import com.liuxi.common.pojo.ErrorCode;
import com.liuxi.merchant.api.exception.BusinessException;
import com.liuxi.merchant.application.vo.RestErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * <p>
 * 统一异常处理
 * </P>
 * @author liu xi
 * @date 2022/4/13 0:29
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public RestErrorResponse runtimeException(Exception ex) {
        // 如果异常是自定义异常
        if (ex instanceof BusinessException) {
            BusinessException businessEx = (BusinessException) ex;
            ErrorCode errorCode = businessEx.getErrorCode();
            log.error("用户自定义异常：{}， 描述：{}", errorCode.getCode(), errorCode.getDesc());
            return new RestErrorResponse(errorCode.getCode(), errorCode.getDesc());
        }
        log.error("系统异常：{}", ex.getMessage());
        return new RestErrorResponse(CommonErrorCode.UNKNOWN.getCode(), ex.getMessage());
    }
}
