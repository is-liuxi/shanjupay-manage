package com.liuxi.merchant.application.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * <p>
 *
 * </P>
 * @author liu xi
 * @date 2022/4/11 23:24
 */
@Configuration
@EnableSwagger2
@ConditionalOnProperty(prefix = "swagger", value = "enable", havingValue = "true")
public class SwaggerConfig {

    @Bean
    public Docket buildDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(buildApiInfo())
                .select()
                // 扫描 API(Controller) 基础包
                .apis(RequestHandlerSelectors.basePackage("com.liuxi.merchant.application.controller"))
                .paths(PathSelectors.any()).build();
    }

    private ApiInfo buildApiInfo() {
        Contact contact = new Contact("苞米豆四号地", "", "");
        return new ApiInfoBuilder()
                .title("闪聚支付-商品应用 API 文档")
                .description("")
                .contact(contact)
                .version("1.0.0").build();
    }
}
