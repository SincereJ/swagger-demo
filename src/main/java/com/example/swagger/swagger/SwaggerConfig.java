package com.example.swagger.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * swagger配置
 */
@Configuration
@EnableSwagger2
@ComponentScan(basePackages = { "com.example.*" })
@Component
public class SwaggerConfig {
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("api swagger document").description("前后端联调swagger api 文档").version("1.0")
				.build();
	}
}