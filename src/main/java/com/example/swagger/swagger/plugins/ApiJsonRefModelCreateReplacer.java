package com.example.swagger.swagger.plugins;

import com.example.swagger.config.ApiJsonClassLoader;
import com.example.swagger.swagger.contexts.ApiJsonRefContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import springfox.documentation.spi.DocumentationType;

import static springfox.documentation.swagger.common.SwaggerPluginSupport.pluginDoesApply;

@Component
public class ApiJsonRefModelCreateReplacer implements ApiJsonRefModelReplacePlugin {
    private static final Logger LOG = LoggerFactory.getLogger(ApiJsonRefModelCreateReplacer.class);

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
        Class<?> clazz = null;
        try {
            clazz = apiJsonClassLoader.getLoader().loadClass(name);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if(clazz != null) context.setClazz(clazz);
    }
}
