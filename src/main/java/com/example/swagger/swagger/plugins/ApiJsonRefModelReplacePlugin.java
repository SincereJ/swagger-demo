package com.example.swagger.swagger.plugins;

import com.example.swagger.swagger.contexts.ApiJsonRefContext;
import org.springframework.plugin.core.Plugin;
import springfox.documentation.spi.DocumentationType;

public interface ApiJsonRefModelReplacePlugin extends Plugin<DocumentationType>, PluginOrder {

    void apply(ApiJsonRefContext context);

}
