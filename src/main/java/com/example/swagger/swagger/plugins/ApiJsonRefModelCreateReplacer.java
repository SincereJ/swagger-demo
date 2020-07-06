package com.example.swagger.swagger.plugins;

import com.example.swagger.config.ApiJsonClassLoader;
import com.example.swagger.swagger.contexts.ApiJsonRefContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import springfox.documentation.spi.DocumentationType;

import static springfox.documentation.swagger.common.SwaggerPluginSupport.pluginDoesApply;

@Component
public class ApiJsonRefModelCreateReplacer implements ApiJsonRefModelReplacePlugin {

    @Autowired
    private ApiJsonClassLoader apiJsonClassLoader;

    @Override
    public Integer order() {
        return 5;
    }

    @Override
    public boolean supports(DocumentationType delimiter) {
        return pluginDoesApply(delimiter);
    }

    @Override
    public void apply(ApiJsonRefContext context) {
        String name = context.getDocumentation().getName();
        byte[] code = context.getCode();
        Class<?> clazz = apiJsonClassLoader.defineClassInstance(name, code, 0, code.length);
        if(clazz != null) context.setClazz(clazz);
    }
}