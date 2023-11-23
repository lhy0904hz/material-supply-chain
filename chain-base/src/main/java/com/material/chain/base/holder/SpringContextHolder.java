package com.material.chain.base.holder;

import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Collection;
import java.util.Map;

@Slf4j
public class SpringContextHolder implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    /**
     * 获取applicationContext
     */
    public static ApplicationContext getApplicationContext() {
        checkApplicationContext();
        return applicationContext;
    }

    @Override
    public void setApplicationContext(@NotNull ApplicationContext applicationContext) {
        SpringContextHolder.applicationContext = applicationContext;
        log.info("SpringContextHolder start!");
    }

    /**
     * 获取spring bean
     */
    public static <T> T getBean(String beanName) {
        checkApplicationContext();
        return (T) applicationContext.getBean(beanName);
    }

    /**
     * 获取spring bean
     */
    public static <T> T getBean(String beanName, Class<T> clazz) {
        checkApplicationContext();
        return applicationContext.getBean(beanName, clazz);
    }

    /**
     * 获取spring bean
     */
    public static <T> T getBean(Class<T> clazz) {
        checkApplicationContext();
        Map<String, T> beans = applicationContext.getBeansOfType(clazz);
        if (beans.isEmpty()) {
            return null;
        }
        return beans.values().iterator().next();
    }

    public static <T> Collection<T> getBeansOfType(Class<T> clazz) {
        checkApplicationContext();
        Map<String, T> beans = applicationContext.getBeansOfType(clazz);
        if (beans.isEmpty()) {
            return null;
        }
        return beans.values();
    }
    private static void checkApplicationContext() {
        if (applicationContext == null) {
            throw new IllegalStateException("applicaitonContext为空");
        }
    }
}
