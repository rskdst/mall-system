package com.mall.goods.controller;


import com.mall.goods.pojo.Sku;
import com.mall.goods.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @RequestMapping(value = "/allgoods",method = RequestMethod.GET)
    public List<Sku> finaAll(){
        return goodsService.findAll();
    }
    @RequestMapping(value = "ggggg")
    public String gggg(){

        return "111111111111111111";
    }
}
