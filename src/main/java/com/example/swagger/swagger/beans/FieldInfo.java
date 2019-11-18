package com.example.swagger.swagger.beans;

import lombok.Data;

@Data
public class FieldInfo {

    private Class type;
    private String key;
    private Object example;

    public FieldInfo(){}

    public FieldInfo(Class type, String key, Object example){
        this.type = type;
        this.key = key;
        this.example = example;
    }
}
