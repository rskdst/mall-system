package com.mall.goods.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.mall.goods.pojo.Sku;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface GoodsDao extends BaseMapper<Sku> {

    @Select("select * from goods_sku")
    List<Sku> getAll();
}
