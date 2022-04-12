package com.liuxi.merchant.application.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * <p>
 *
 * </P>
 * @author liu xi
 * @date 2022/4/12 11:23
 */
@Component
public class SpringContextConfig implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    /**
     * 设置容器上下文
     * @param applicationContext
     * @throws BeansException
     */
    @Override
    @SuppressWarnings("all")
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextConfig.applicationContext = applicationContext;
    }

    /**
     * 获取 bean
     * @param clazz
     * @param <T>
     * @return
     */
    public static  <T> T getBean(Class<T> clazz) {

        return applicationContext.getBean(clazz);
    }
}
