package com.liuxi.common.util;

import java.util.UUID;

/**
 * <p>
 * 随机UUID工具
 * 效果：b5e8863950d649ffa5a372dd0e951416
 * </P>
 * @author liu xi
 * @date 2022/4/11 21:06
 */
public class RandomUuidUtil {

    public static String getUUID() {
        UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
        String uuidStr = str.replace("-", "");
        return uuidStr;
    }
}
