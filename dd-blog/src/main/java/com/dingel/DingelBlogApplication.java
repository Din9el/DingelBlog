package com.dingel;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.dingel.mapper")
@EnableScheduling
public class DingelBlogApplication {
    public static void main(String[] args) {
        SpringApplication.run(DingelBlogApplication.class,args);
    }
}
