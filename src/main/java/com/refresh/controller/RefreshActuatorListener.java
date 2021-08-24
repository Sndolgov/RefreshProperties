package com.refresh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.scope.refresh.RefreshScopeRefreshedEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

import lombok.SneakyThrows;

@Configuration
public class RefreshActuatorListener {

    @Autowired
    AppProperties appProperties;

    @SneakyThrows
    @EventListener(RefreshScopeRefreshedEvent.class)
    public void onRefresh(RefreshScopeRefreshedEvent event) {
        appProperties.reload();
    }

}
