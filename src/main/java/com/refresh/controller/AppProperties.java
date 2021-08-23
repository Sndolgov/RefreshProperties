package com.refresh.controller;

import org.springframework.stereotype.Component;

@Component
public class AppProperties extends ReloadableProperties {

    public String dynamicProperty() {
        return environment.getProperty("my.property");
    }
    public String anotherDynamicProperty() {
        return environment.getProperty("another.dynamic.prop");
    }
    @Override
    protected void propertiesReloaded() {
        System.out.println("reloaded");
        // do something after a change in property values was done
    }
}
