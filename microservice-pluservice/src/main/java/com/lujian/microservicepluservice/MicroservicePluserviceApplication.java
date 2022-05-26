package com.lujian.microservicepluservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.lujian.microservicepluservice.mapper")
public class MicroservicePluserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroservicePluserviceApplication.class, args);
    }

}
