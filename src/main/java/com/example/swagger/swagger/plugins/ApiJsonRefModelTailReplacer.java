package com.example.swagger.swagger.plugins;

import com.example.swagger.swagger.contexts.ApiJsonRefContext;
import com.fasterxml.classmate.ResolvedType;
import com.fasterxml.classmate.TypeResolver;
import org.springframework.stereotype.Component;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.RequestMappingContext;

import static springfox.documentation.swagger.common.SwaggerPluginSupport.pluginDoesApply;

@Component
public class ApiJsonRefModelTailReplacer implements ApiJsonRefModelReplacePlugin {
    @Override
    public Integer order() {
        return 10;
    }

    @Override
    public boolean supports(DocumentationType delimiter) {
        return pluginDoesApply(delimiter);
    }

    @Override
    public void apply(ApiJsonRefContext context) {
        RequestMappingContext requestMappingContext = context.getRequestMappingContext();
        TypeResolver typeResolver = context.getTypeResolver();
        Class<?> clazz = context.getClazz();
        ResolvedType returnType = typeResolver.resolve(clazz);

        requestMappingContext.operationModelsBuilder().addInputParam(returnType);
    }
}
