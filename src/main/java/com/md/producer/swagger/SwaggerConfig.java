package com.md.producer.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
public class SwaggerConfig {

    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String AUTH_REQUIRED_PATTERN = "/api/v1/hello/.*";

    @Bean
    public Docket api() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .groupName("V1")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.md.producer.controller.v1"))
                .paths(PathSelectors.regex("/((?!error).)*"))
                .build();

        return docket;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Producer Rest API")
                .description("REST API")
                .version("1.0.0")
                .build();
    }
}
