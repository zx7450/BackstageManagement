package com.zx.daoyundev;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zx.daoyundev.mapper")
public class DaoyundevApplication {

    public static void main(String[] args) {
        SpringApplication.run(DaoyundevApplication.class, args);
    }

}
