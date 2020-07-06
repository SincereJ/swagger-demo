package com.example.swagger.unuse;

import com.example.swagger.swagger.annos.ApiJsonProperty;
import com.example.swagger.swagger.utils.SwaggerASMUtil;
import com.fasterxml.classmate.ResolvedType;
import com.fasterxml.classmate.TypeResolver;
import org.springframework.beans.factory.annotation.Autowired;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ResolvedMethodParameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.ParameterBuilderPlugin;
import springfox.documentation.spi.service.contexts.ParameterContext;

import java.util.Map;
import java.util.Set;

//@Component
//@Order  //plugin加载顺序，默认是最后加载
public class MapApiReader_bak extends ClassLoader implements ParameterBuilderPlugin {
    @Autowired
    private TypeResolver typeResolver;

    //@Override
    public void apply(ParameterContext parameterContext) {
        Map<String, Object> maps = SwaggerMapContext.getMap();
        ResolvedMethodParameter methodParameter = parameterContext.resolvedMethodParameter();
        String requestMappingPatternName = parameterContext.getOperationContext().requestMappingPattern();

        String parameterName = methodParameter.defaultName().get();
        System.out.println("MapApiReader.java apply:" + parameterName);
        
        if (methodParameter.getParameterType().canCreateSubtype(Map.class) || methodParameter.getParameterType().canCreateSubtype(String.class)) {
            System.out.println("methodParameter.getParameterType():" + methodParameter.getParameterType().getTypeName());

            String name = SwaggerASMUtil.returnClassName(requestMappingPatternName, "H" + parameterName);

            ApiJsonProperty[] properties = (ApiJsonProperty[]) maps.get(requestMappingPatternName);
            byte[] cs = SwaggerASMUtil.createRefModel(properties, name);
            assert cs != null;
            Class<?> hw = this.defineClass(name, cs, 0, cs.length);
            ResolvedType resolve = typeResolver.resolve(hw);

            Set<ResolvedType> models = parameterContext.getDocumentationContext().getAdditionalModels();
            if(!models.contains(resolve)){
                parameterContext.getDocumentationContext().getAdditionalModels().add(resolve);
                parameterContext.parameterBuilder().parameterType("body").modelRef(new ModelRef(name)).name(name);
            }
        }
    }

    @Override
    public boolean supports(DocumentationType delimiter) {
        return true;
    }
}
