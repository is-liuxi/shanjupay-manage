package com.liuxi.merchant.appliation;

import com.aliyun.oss.OSS;
import com.liuxi.merchant.application.MerchantApplicationBootstrap;
import com.liuxi.merchant.application.vo.AliYun;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </P>
 * @author liu xi
 * @date 2022/4/13 6:31
 */
@SpringBootTest(classes = MerchantApplicationBootstrap.class)
@RunWith(SpringRunner.class)
public class OSSTest {

    @Autowired
    private OSS ossClient;

    @Autowired
    private AliYun aliYun;

    @Test
    public void uploadAliYunOSS() throws Exception {
        String image = "E:\\springboot.png";
        FileInputStream fis = new FileInputStream(new File(image));
        ossClient.putObject(aliYun.getBucketName(), filePath("a.png"), fis);
    }

    /**
     * 生成上传到 OSS 后的路径
     * @param imgName
     * @return
     */
    private String filePath(String imgName) {
        LocalDateTime now = LocalDateTime.now();
        return "images" + "/"
                + now.getYear() + "/"
                + now.getMonth().getValue() + "/"
                + now.getDayOfMonth() + "/" +
                + System.currentTimeMillis() + "."
                + StringUtils.substringAfter(imgName, ".");
    }
}
