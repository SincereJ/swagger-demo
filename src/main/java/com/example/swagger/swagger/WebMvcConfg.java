package com.example.swagger.swagger;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 拦截器配置
 */
@Configuration
public class WebMvcConfg implements WebMvcConfigurer {
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    	registry.addResourceHandler("swagger-ui.html")
        .addResourceLocations("classpath:/META-INF/resources/");

		registry.addResourceHandler("/webjars/**")
		        .addResourceLocations("classpath:/META-INF/resources/webjars/");
		
		registry.addResourceHandler("/swagger-resources/**")
		        .addResourceLocations("classpath:/META-INF/resources/swagger-resources/");
		
		registry.addResourceHandler("/swagger/**")
		        .addResourceLocations("classpath:/META-INF/resources/swagger*");
		
		registry.addResourceHandler("/v2/api-docs/**")
		        .addResourceLocations("classpath:/META-INF/resources/v2/api-docs/");
    }

}
