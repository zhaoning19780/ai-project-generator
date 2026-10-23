package com.gpjy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.gpjy.mapper")
public class AiGeneratorMotherApplication {

    public static void main(String[] args) {
        SpringApplication.run(AiGeneratorMotherApplication.class, args);
    }

}
