package com.mall.goods.pojo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class Goods implements Serializable {

    private Spu spu;
    private List<Sku> skus;

}
