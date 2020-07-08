package com.example.swagger.swagger.plugins;

import com.example.swagger.swagger.annos.ApiJsonAttribute;
import com.google.common.base.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.schema.ModelPropertyBuilderPlugin;
import springfox.documentation.spi.schema.contexts.ModelPropertyContext;
import springfox.documentation.spring.web.DescriptionResolver;
import springfox.documentation.swagger.common.SwaggerPluginSupport;

import static com.example.swagger.swagger.schema.ApiJsonAttributes.*;
import static springfox.documentation.schema.Annotations.findPropertyAnnotation;

//@Component
//@Order(SwaggerPluginSupport.SWAGGER_PLUGIN_ORDER)
public class ApiJsonAttributeBuilder {//implements ModelPropertyBuilderPlugin {
    private final DescriptionResolver descriptions;

    @Autowired
    public ApiJsonAttributeBuilder(DescriptionResolver descriptions) {
        this.descriptions = descriptions;
    }

    //@Override
    public void apply(ModelPropertyContext context) {
        Optional<ApiJsonAttribute> annotation = Optional.absent();

        if (context.getAnnotatedElement().isPresent()) {
            annotation = annotation.or(findApiModePropertyAnnotation(context.getAnnotatedElement().get()));
        }
        if (context.getBeanPropertyDefinition().isPresent()) {
            annotation = annotation.or(findPropertyAnnotation(context.getBeanPropertyDefinition().get(), ApiJsonAttribute.class));
        }
        if (annotation.isPresent()) {
            context.getBuilder()
                    .allowableValues(annotation.transform(toAllowableValues()).orNull())
                    .required(annotation.transform(toIsRequired()).or(false))
                    .description(annotation.transform(toDescription(descriptions)).orNull())
                    .isHidden(annotation.transform(toHidden()).or(false))
                    .type(annotation.transform(toType(context.getResolver())).orNull())
                    .position(annotation.transform(toPosition()).or(0))
                    .example(annotation.transform(toExample()).orNull());
        }
    }

    //@Override
    public boolean supports(DocumentationType delimiter) {
        return SwaggerPluginSupport.pluginDoesApply(delimiter);
    }

}
