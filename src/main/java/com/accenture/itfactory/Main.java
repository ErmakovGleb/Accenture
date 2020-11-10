package com.accenture.itfactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.accenture.itfactory")
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }


}
