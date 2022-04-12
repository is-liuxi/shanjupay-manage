package com.liuxi.merchant.application.controller;

import com.liuxi.merchant.api.vo.ResultVo;

/**
 * <p>
 * 统一结果返回
 * </P>
 * @author liu xi
 * @date 2022/4/12 23:19
 */
public abstract class AbstractResultController<T> {

    public ResultVo<T> responseJson(int code, String msg, T data) {
        ResultVo<T> result = new ResultVo<>();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }
}
