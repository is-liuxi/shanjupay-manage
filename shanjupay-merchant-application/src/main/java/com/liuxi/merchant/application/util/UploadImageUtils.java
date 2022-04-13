package com.liuxi.merchant.application.util;

import org.apache.commons.lang.StringUtils;

import java.time.LocalDateTime;

/**
 * <p>
 *
 * </P>
 * @author liu xi
 * @date 2022/4/13 8:12
 */
public class UploadImageUtils {

    /**
     * 生成上传到 OSS 后的路径
     * images/year/month/day/xxx.jpg
     * @param imgName
     * @return
     */
    public static String filePath(String imgName) {
        LocalDateTime now = LocalDateTime.now();
        return "images" + "/"
                + now.getYear() + "/"
                + now.getMonth().getValue() + "/"
                + now.getDayOfMonth() + "/" +
                + System.currentTimeMillis() + "."
                + StringUtils.substringAfter(imgName, ".");
    }
}
