package com.zx.daoyundev;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author zx
 * @date 2021/6/23 22:20
 */
@Configuration
@EnableSwagger2
@ComponentScan(basePackages = "com.zx.daoyundev.controller")
public class SwaggerConfig {
    @Bean //作为bean纳入spring容器
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .paths(PathSelectors.any())
                .apis(RequestHandlerSelectors.basePackage("com.zx.daoyundev.controller"))
                .build();
    }
    private ApiInfo apiInfo(){
        return  new ApiInfoBuilder()
                .title("到云API接口文档")
                .description("API接口文档，及相关接口的说明")
                .version("1.0.0")
                .build();
    }
}
