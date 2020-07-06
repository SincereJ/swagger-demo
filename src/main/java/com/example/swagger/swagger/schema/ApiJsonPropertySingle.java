package com.example.swagger.swagger.schema;

public class ApiJsonPropertySingle {

    private final Class<?> type;
    private final String key;
    private final String example;
    private final String description;

    public ApiJsonPropertySingle(Class<?> type, String key, String example, String description){
        this.type = type;
        this.key = key;
        this.example = example;
        this.description = description;
    }

    public Class<?> getType() {
        return type;
    }

    public String getKey() {
        return key;
    }

    public String getExample() {
        return example;
    }

    public String getDescription() {
        return description;
    }
}
