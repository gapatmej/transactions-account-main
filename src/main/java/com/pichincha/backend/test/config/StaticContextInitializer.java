package com.pichincha.backend.test.config;

import com.pichincha.backend.test.rest.util.HeaderUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class StaticContextInitializer {

    @Value("${api.name}")
    private String applicationName;

    public StaticContextInitializer() {
    }

    @PostConstruct
    public void init() {
        HeaderUtil.setApplicationName(this.applicationName);
    }


}

