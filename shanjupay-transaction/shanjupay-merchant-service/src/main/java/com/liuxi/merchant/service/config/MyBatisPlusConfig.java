package com.liuxi.merchant.service.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * MyBatisPlus 配置
 * </P>
 * @author liu xi
 * @date 2022/4/11 22:52
 */
@Configuration
@MapperScan(basePackages = "com.liuxi.merchant.service.mapper")
public class MyBatisPlusConfig {

    /**
     * 分页插件，自动识别数据库类型
     * @return
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    /**
     * 启用性能分析插件
     * @return
     */
    @Bean
    public PerformanceInterceptor performanceInterceptor() {
        return new PerformanceInterceptor();
    }
}
