package com.udemy.springcloud.ms.cursos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MsCursosApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsCursosApplication.class, args);
    }

}
