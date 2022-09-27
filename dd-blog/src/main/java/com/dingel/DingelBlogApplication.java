package com.dingel;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.dingel.mapper")
public class DingelBlogApplication {
    public static void main(String[] args) {
        SpringApplication.run(DingelBlogApplication.class,args);
    }
}
