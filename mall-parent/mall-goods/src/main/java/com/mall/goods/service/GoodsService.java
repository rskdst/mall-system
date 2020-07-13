package com.mall.goods.service;

import com.mall.goods.dao.GoodsDao;
import com.mall.goods.pojo.Sku;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsService {
    @Autowired
    private GoodsDao goodsDao;

    public List<Sku> findAll(){
        return goodsDao.getAll();
    }

}
