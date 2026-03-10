package com.xitian.djrlpwst;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.xitian.djrlpwst.mapper")
public class DjrLpwstApplication {

    public static void main(String[] args) {
        SpringApplication.run(DjrLpwstApplication.class, args);
    }

}
