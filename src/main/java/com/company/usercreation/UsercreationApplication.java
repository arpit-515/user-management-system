package com.company.usercreation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class UsercreationApplication {

    public static void main(String[] args) {
        SpringApplication.run(UsercreationApplication.class, args);
    }

}
