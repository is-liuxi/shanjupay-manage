package com.liuxi.merchant.application.exception;

import com.liuxi.common.pojo.ErrorCode;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <p>
 *
 * </P>
 * @author liu xi
 * @date 2022/4/13 4:02
 */
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
public class BusinessException extends RuntimeException {

    private ErrorCode errorCode;

    public BusinessException(ErrorCode errorCode) {
        super();
        this.errorCode = errorCode;
    }
}
