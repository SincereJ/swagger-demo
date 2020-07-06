package com.example.swagger.swagger.config;

import com.example.swagger.swagger.contexts.ApiJsonDocumentationContext;
import com.example.swagger.swagger.contexts.ApiJsonRefContext;
import com.example.swagger.swagger.plugins.ApiJsonObjectPlugin;
import com.example.swagger.swagger.plugins.ApiJsonPropertyPlugin;
import com.example.swagger.swagger.plugins.ApiJsonRefModelReplacePlugin;
import com.example.swagger.swagger.plugins.PluginOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.plugin.core.PluginRegistry;
import org.springframework.stereotype.Component;
import springfox.documentation.spi.DocumentationType;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ApiJsonPluginManager {

    @Autowired
    @Qualifier("apiJsonObjectPluginRegistry")
    private PluginRegistry<ApiJsonObjectPlugin, DocumentationType> apiJsonObjectPlugins;
    @Autowired
    @Qualifier("apiJsonPropertyPluginRegistry")
    private PluginRegistry<ApiJsonPropertyPlugin, DocumentationType> apiJsonPropertyPlugins;
    @Autowired
    @Qualifier("apiJsonRefModelReplacePluginRegistry")
    private PluginRegistry<ApiJsonRefModelReplacePlugin,DocumentationType> apiJsonRefModelReplacePlugins;


    public void apiJsonObject(ApiJsonDocumentationContext context) {
        for (ApiJsonObjectPlugin each : apiJsonObjectPlugins.getPluginsFor(context.getDocumentationType())) {
            each.apply(context);
        }
    }

    public void apiJsonProperty(ApiJsonDocumentationContext context) {
        for (ApiJsonPropertyPlugin each : apiJsonPropertyPlugins.getPluginsFor(context.getDocumentationType())) {
            each.apply(context);
        }
    }

    public void apiJsonReplace(ApiJsonRefContext context){
        List<ApiJsonRefModelReplacePlugin> plugins = apiJsonRefModelReplacePlugins.getPlugins();
        if(!plugins.isEmpty()){
            plugins = plugins.stream().sorted(Comparator.comparingInt(PluginOrder::order)).collect(Collectors.toList());
            for (ApiJsonRefModelReplacePlugin each : plugins) {
                each.apply(context);
            }
        }
    }

}
