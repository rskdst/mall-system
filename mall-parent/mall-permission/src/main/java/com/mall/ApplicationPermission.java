package com.mall;

import com.mall.utils.IdWorker;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@CrossOrigin
@MapperScan("com.mall.mapper")
public class ApplicationPermission {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationPermission.class,args);

    }
    @Bean
    public IdWorker idWorker() {
        return new IdWorker(1,1);
    }
}
