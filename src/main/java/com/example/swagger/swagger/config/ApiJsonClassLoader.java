package com.example.swagger.swagger.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.loader.LaunchedURLClassLoader;

import java.net.MalformedURLException;
import java.net.URL;

import static com.example.swagger.swagger.config.ApiJsonDocumentationConfiguration.TEMP_CLASS;

public class ApiJsonClassLoader extends ClassLoader {

    private LaunchedURLClassLoader loader;

    public ApiJsonClassLoader(){
        loader = getURLClassLoader("file://" + TEMP_CLASS);
    }

    public Class<?> defineClassInstance(String name, byte[] b, int off, int len) {
        return defineClass(name, b, off, len);
    }

    private LaunchedURLClassLoader getURLClassLoader(String fileNames){
        if(loader == null){
            if(StringUtils.isNotBlank(fileNames)){
                URL[] urls = new URL[1];
                try {
                    urls[0] = new URL(fileNames);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                    throw new RuntimeException("加载lib目录下jar文件出错！",e);
                }
                loader = new LaunchedURLClassLoader(urls,Thread.currentThread().getContextClassLoader());
                Thread.currentThread().setContextClassLoader(loader);
            }
        }
        return loader;
    }

    public LaunchedURLClassLoader getLoader() {
        return loader;
    }
}
