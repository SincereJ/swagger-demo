package com.example.swagger.swagger.contexts;

import com.example.swagger.swagger.documentation.ApiJsonDocumentation;
import com.fasterxml.classmate.TypeResolver;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.RequestMappingContext;

public class ApiJsonRefContext {

    private final RequestMappingContext requestMappingContext;
    private final ApiJsonDocumentation documentation;
    private final TypeResolver typeResolver;
    private byte[] code;
    private Class<?> clazz;
    private TypeResolver returnType;

    public ApiJsonRefContext(RequestMappingContext requestMappingContext,
                             ApiJsonDocumentation documentation, TypeResolver typeResolver){
        this.requestMappingContext = requestMappingContext;
        this.documentation = documentation;
        this.typeResolver = typeResolver;
    }

    public ApiJsonDocumentation getDocumentation() {
        return documentation;
    }

    public TypeResolver getTypeResolver() {
        return typeResolver;
    }

    public DocumentationType getDocumentationType() {
        return requestMappingContext.getDocumentationContext().getDocumentationType();
    }

    public byte[] getCode() {
        return code;
    }

    public void setCode(byte[] code) {
        this.code = code;
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public void setClazz(Class<?> clazz) {
        this.clazz = clazz;
    }

    public TypeResolver getReturnType() {
        return returnType;
    }

    public void setReturnType(TypeResolver returnType) {
        this.returnType = returnType;
    }

    public RequestMappingContext getRequestMappingContext() {
        return requestMappingContext;
    }
}
