package com.refresh.controller;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:name.properties")
@ConfigurationProperties("my")
@RefreshScope
//@Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
//@RequestScope
public class NamePropertiesMap {

    private Map<String, String> value;
    @Autowired
    private ApplicationContext applicationContext;

    @PostConstruct
    private void init() {
        System.out.println("init");
    }

    public Map<String, String> getValue() {
        return value;
    }

    public void setValue(Map<String, String> value) {
        this.value = value;
    }
}
