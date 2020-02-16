package com.pds.access;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class AccessApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(AccessApplication.class, args);
    }

}
