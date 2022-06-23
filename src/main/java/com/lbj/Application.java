package com.lbj;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;

import java.security.NoSuchAlgorithmException;


@SpringBootApplication
public class Application {
    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) throws NoSuchAlgorithmException {
        SpringApplication.run(Application.class, args);
        LOGGER.info("Demo Start success........");
    }

}
