package com.mall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan({"com.mall.goods.dao"})
public class ApplicationGoods {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationGoods.class,args);
    }
}
