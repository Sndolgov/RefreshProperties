package com.refresh.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
//@RefreshScope
public class Controller {

    @Autowired
    private NameProperties nameProperties;

    @Autowired
    private AppProperties appProperties;

    @Value("${my.property:Hello default}")
    private String value;

    @GetMapping("/value")
    public String getValue(){
        return value + "\n" + nameProperties.getProperty() + "\n" + appProperties.dynamicProperty();
    }

    @GetMapping("/reload")
    public void reload() throws IOException {
        appProperties.reload();
    }

}
