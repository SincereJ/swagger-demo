package com.example.swagger.swagger.contexts;

import com.example.swagger.swagger.annos.ApiJsonObject;
import com.example.swagger.swagger.schema.ApiJsonPropertySingle;
import springfox.documentation.spi.service.contexts.RequestMappingContext;

import java.util.List;

public class ApiJsonDocumentationContextBuilder {
    private final RequestMappingContext requestMappingContext;
    private final ApiJsonObject annotations;
    private String name;
    private List<ApiJsonPropertySingle> properties;

    public ApiJsonDocumentationContextBuilder(RequestMappingContext requestMappingContext,
                                              ApiJsonObject annotations
                                              ){
        this.requestMappingContext = requestMappingContext;
        this.annotations = annotations;
    }

    public ApiJsonDocumentationContextBuilder name(String name){
        this.name = name;
        return this;
    }

    public ApiJsonDocumentationContextBuilder properties(List<ApiJsonPropertySingle> properties){
        this.properties = properties;
        return this;
    }

    public ApiJsonDocumentationContext build(){
        return new ApiJsonDocumentationContext(
                requestMappingContext,
                annotations
        );
    }

}
