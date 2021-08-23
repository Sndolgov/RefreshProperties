package com.refresh.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RefreshScope
public class Controller {

    @Value("${my.property:Hello default}")
    private String value;

    @GetMapping("/value")
    public String getValue(){
        return value;
    }

}
