package com.example.swagger.swagger.config;

import org.springframework.boot.loader.LaunchedURLClassLoader;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ApiJsonClassLoader extends ClassLoader {

    private final static String DEFINE_CLASS = "defineClass0";
    private Method method = null;

    public ApiJsonClassLoader(){
        try {
            Class<?> proxy = Proxy.class;
            method = proxy.getDeclaredMethod(DEFINE_CLASS, ClassLoader.class,String.class,byte[].class,int.class,int.class);
            method.setAccessible(true);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public Class<?> defineClassInstance(String name, byte[] b, int off, int len) {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        if(loader instanceof LaunchedURLClassLoader){
            try {
                return (Class<?>) method.invoke(null, loader, name, b, off, len);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return defineClass(name, b, off, len);
    }
}
