package com.example.swagger.swagger.config;

import com.example.swagger.swagger.annos.ApiJsonProperty;
import com.fasterxml.classmate.TypeResolver;
import com.google.common.base.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ResolvedMethodParameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.ParameterBuilderPlugin;
import springfox.documentation.spi.service.contexts.OperationContext;
import springfox.documentation.spi.service.contexts.ParameterContext;

import java.util.Map;

@Component
@Order   //plugin加载顺序，默认是最后加载
public class MapApiReader extends ClassLoader implements ParameterBuilderPlugin {
    @Autowired
    private TypeResolver typeResolver;

    //@Override
    public void apply(ParameterContext parameterContext) {
        Map<String, Object> maps = SwaggerMapContext.getMap();

        ResolvedMethodParameter methodParameter = parameterContext.resolvedMethodParameter();
        OperationContext operationContext = parameterContext.getOperationContext();
        String requestMappingPatternName = operationContext.requestMappingPattern();

        Optional<String> parameterNameOptional = methodParameter.defaultName();
        String parameterName = parameterNameOptional.get();

        if (methodParameter.getParameterType().canCreateSubtype(Map.class) || methodParameter.getParameterType().canCreateSubtype(String.class)) {

            String name = "H" + parameterName;
            name = SwaggerASMUtil.returnClassName(requestMappingPatternName,name);

            ApiJsonProperty[] properties = (ApiJsonProperty[]) maps.get(requestMappingPatternName);

            byte[] cs = SwaggerASMUtil.createRefModel(properties,name);
            Class hw = this.defineClass(name, cs, 0, cs.length);

            parameterContext.getDocumentationContext().getAdditionalModels().add(typeResolver.resolve(hw));
            parameterContext.parameterBuilder().parameterType("body").modelRef(new ModelRef(name)).name(name);
        }
    }

    @Override
    public boolean supports(DocumentationType delimiter) {
        return true;
    }
}
