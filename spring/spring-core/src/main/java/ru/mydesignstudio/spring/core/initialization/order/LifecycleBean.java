package ru.mydesignstudio.spring.core.initialization.order;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.annotation.PostConstruct;

public class LifecycleBean implements InitializingBean, BeanNameAware, ApplicationContextAware {
    @PostConstruct
    public void postConstruct() {
        System.out.println("Post construct");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("After properties set");
    }

    public void init() {
        System.out.println("Init");
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("Set bean name");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("Set application context");
    }
}
