package com.example.swagger.swagger.contexts;

import com.example.swagger.swagger.annos.ApiJsonObject;
import com.example.swagger.swagger.schema.ApiJsonPropertySingle;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.RequestMappingContext;

import java.util.List;

public class ApiJsonDocumentationContext {
    private final RequestMappingContext requestMappingContext;
    private final ApiJsonObject annotations;
    private String name;
    private List<ApiJsonPropertySingle> properties;

    public ApiJsonDocumentationContext(RequestMappingContext requestMappingContext,
                                       ApiJsonObject annotations
                                       ){
        this.requestMappingContext = requestMappingContext;
        this.annotations = annotations;
    }

    public RequestMappingContext getRequestMappingContext() {
        return requestMappingContext;
    }

    public ApiJsonObject getAnnotations() {
        return annotations;
    }

    public ApiJsonDocumentationContext setName(String name) {
        this.name = name;
        return this;
    }

    public ApiJsonDocumentationContext setProperties(List<ApiJsonPropertySingle> properties) {
        this.properties = properties;
        return this;
    }

    public String getName() {
        return name;
    }

    public List<ApiJsonPropertySingle> getProperties() {
        return properties;
    }

    public DocumentationType getDocumentationType() {
        return requestMappingContext.getDocumentationContext().getDocumentationType();
    }
}
