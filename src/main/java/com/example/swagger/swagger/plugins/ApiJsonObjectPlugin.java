package com.example.swagger.swagger.plugins;

import com.example.swagger.swagger.contexts.ApiJsonDocumentationContext;
import org.springframework.plugin.core.Plugin;
import springfox.documentation.spi.DocumentationType;

public interface ApiJsonObjectPlugin extends Plugin<DocumentationType> {

    void apply(ApiJsonDocumentationContext context);

}
