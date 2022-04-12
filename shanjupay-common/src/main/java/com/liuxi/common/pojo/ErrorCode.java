package com.liuxi.common.pojo;

/**
 * <p>
 * 错误状态码、描述返回
 * </P>
 * @author liu xi
 * @date 2022/4/11 20:51
 */
public interface ErrorCode {

    /**
     * 错误状态码
     * @return
     */
    int getCode();

    /**
     * 描述
     * @return
     */
    String getDesc();

}
