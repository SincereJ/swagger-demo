package com.example.swagger.swagger;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringTool implements ApplicationContextAware {
    /**
     * applicationContext 对象
     */
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if(SpringTool.applicationContext == null){
            SpringTool.applicationContext = applicationContext;
        }
    }

    /**
     * @return the applicationContext
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }
    /**
     * 根据bean名称获取 spring实例 <br/>
     * 注意：spring 默认的bean的名称 一般为bean实现类 的首字母小写的类名 <br/>
     * eg:LogServiceImpl 如果没有指定bean的名称 则bean一般默认为：logServiceImpl
     * @param name
     * @return
     */
    public static  Object getBean(String name){
        return getApplicationContext().getBean(name);
    }
}