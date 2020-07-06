package com.example.swagger.swagger.config;

import com.example.swagger.config.ApiJsonClassLoader;
import com.example.swagger.swagger.plugins.ApiJsonObjectPlugin;
import com.example.swagger.swagger.plugins.ApiJsonPropertyPlugin;
import com.example.swagger.swagger.plugins.ApiJsonRefModelReplacePlugin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.plugin.core.config.EnablePluginRegistries;

@Configuration
@ComponentScan(basePackages = {
        "com.example.swagger.swagger.plugins",
        "com.example.swagger.swagger.config",
        "com.example.swagger.swagger.scanners"
})
@EnablePluginRegistries({
        ApiJsonObjectPlugin.class,
        ApiJsonPropertyPlugin.class,
        ApiJsonRefModelReplacePlugin.class
})
public class ApiJsonDocumentationConfiguration {

    @Bean
    public ApiJsonClassLoader apiJsonClassLoader(){
        return new ApiJsonClassLoader();
    }
}
