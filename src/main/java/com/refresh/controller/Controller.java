package com.refresh.controller;

import java.io.IOException;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RefreshScope
public class Controller {

    @Autowired
    private NameProperties nameProperties;

    @Autowired
    private NamePropertiesMap namePropertiesMap;

    @Autowired
    private AppProperties appProperties;

    @Value("${my.value.property:Hello default}")
    private String value;

    @GetMapping("/value")
    public String getValue(){
        return value
                + "\n" + nameProperties.getProperty()
                + "\n" + appProperties.dynamicProperty()
                ;
    }

    @GetMapping("/values")
    public Collection<String> getValues(){
        return namePropertiesMap.getValue().values();
    }


    @GetMapping("/reload")
    public void reload() throws IOException {
        appProperties.reload();
    }

}
