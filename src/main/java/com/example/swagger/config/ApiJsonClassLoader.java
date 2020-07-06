package com.example.swagger.config;

public class ApiJsonClassLoader extends ClassLoader {

    public ApiJsonClassLoader(){
        super();
    }

    public Class<?> defineClassInstance(String name, byte[] b, int off, int len){
        return super.defineClass(name,b,off,len);
    }
}
