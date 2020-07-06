package com.example.swagger.swagger.plugins;

import com.example.swagger.swagger.utils.SwaggerASMUtil;
import com.example.swagger.swagger.contexts.ApiJsonDocumentationContext;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.RequestMappingContext;

import java.util.Set;

import static springfox.documentation.swagger.common.SwaggerPluginSupport.pluginDoesApply;

@Component
public class ApiJsonNameAndTypeReader implements ApiJsonObjectPlugin {

    @Override
    public void apply(ApiJsonDocumentationContext context) {
        RequestMappingContext requestMappingContext = context.getRequestMappingContext();
        Set<String> patterns = requestMappingContext.getPatternsCondition().getPatterns();
        if(!patterns.isEmpty()){
            Object[] ops = patterns.toArray();
            String annName = context.getAnnotations().name();
            String name = SwaggerASMUtil.returnClassName((String) ops[0], "H" + annName);

            if(StringUtils.isNotBlank(name)) context.setName(name);
        }
    }

    @Override
    public boolean supports(DocumentationType delimiter) {
        return pluginDoesApply(delimiter);
    }
}
