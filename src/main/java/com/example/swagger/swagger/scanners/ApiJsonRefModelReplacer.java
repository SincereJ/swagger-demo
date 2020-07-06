package com.example.swagger.swagger.scanners;

import com.example.swagger.swagger.config.ApiJsonPluginManager;
import com.example.swagger.swagger.contexts.ApiJsonRefContext;
import org.springframework.stereotype.Component;

@Component
public class ApiJsonRefModelReplacer {

    private final ApiJsonPluginManager apiJsonPluginManager;

    public ApiJsonRefModelReplacer(ApiJsonPluginManager apiJsonPluginManager){
        this.apiJsonPluginManager = apiJsonPluginManager;
    }

    public void replace (ApiJsonRefContext apiJsonRefContext){
        this.apiJsonPluginManager.apiJsonReplace(apiJsonRefContext);
    }
}
