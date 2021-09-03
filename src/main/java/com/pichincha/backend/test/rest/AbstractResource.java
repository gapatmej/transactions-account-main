package com.pichincha.backend.test.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AbstractResource {

    protected final Logger log ;

    public AbstractResource(Class c) {
        this.log = LoggerFactory.getLogger(c);
    }
}
