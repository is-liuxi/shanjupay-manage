package com.liuxi.merchant.api.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * <p>
 *
 * </P>
 * @author liu xi
 * @date 2022/4/12 22:38
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResultVo<T> {

    private String msg;
    private int code;
    private T data;
}
