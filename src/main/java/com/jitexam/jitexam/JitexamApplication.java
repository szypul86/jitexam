package com.jitexam.jitexam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import java.util.Collections;

@SpringBootApplication
public class JitexamApplication {

    public static void main(String[] args) {
        SpringApplication.run(JitexamApplication.class, args);
    }

}
