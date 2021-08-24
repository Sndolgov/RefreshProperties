package com.refresh.controller;

import javax.annotation.PostConstruct;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:name.properties")
@ConfigurationProperties("my.value")
public class NameProperties {
    private String property;

    @PostConstruct
    private void init() {
        System.out.println("init");
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }
}
