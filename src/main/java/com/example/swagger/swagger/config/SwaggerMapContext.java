package com.example.swagger.swagger.config;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class SwaggerMapContext {

    public static final String filterPackage = "com.example";

    private static Map<String, Object> map = new ConcurrentHashMap<>();

    public static Map<String, Object> getMap(){
        return map;
    }

}
