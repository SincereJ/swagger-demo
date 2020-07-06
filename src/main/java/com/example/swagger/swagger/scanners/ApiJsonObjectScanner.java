package com.example.swagger.swagger.scanners;

import com.example.swagger.swagger.config.ApiJsonPluginManager;
import com.example.swagger.swagger.contexts.ApiJsonDocumentationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ApiJsonObjectScanner {
    //private static final Logger LOG = LoggerFactory.getLogger(ApiJsonObjectScanner.class);

    private final ApiJsonPluginManager apiJsonPluginManager;

    @Autowired
    public ApiJsonObjectScanner(ApiJsonPluginManager apiJsonPluginManager){
        this.apiJsonPluginManager = apiJsonPluginManager;
    }

    public ApiJsonPluginManager getApiJsonPluginManager() {
        return apiJsonPluginManager;
    }

    public void read(ApiJsonDocumentationContext context){
        apiJsonPluginManager.apiJsonObject(context);
    }
}
