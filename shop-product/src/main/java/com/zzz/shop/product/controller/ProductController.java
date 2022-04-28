package com.zzz.shop.product.controller;

import com.alibaba.fastjson.JSON;
import com.zzz.shop.bean.model.Product;
import com.zzz.shop.product.service.ProductService;
import com.zzz.shop.utils.constants.HttpCode;
import com.zzz.shop.utils.resp.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zzz
 * 2022-04-27 15:00
 */
@Slf4j
@RestController
public class ProductController {

    @Autowired
    private ProductService productService;


    @GetMapping("/get/{id}")
    public Product getProductById(@PathVariable("id") Long id){
        Product product = productService.getProductById(id);
        log.info("获取到的商品信息为：{}", JSON.toJSONString(product));
        return product;
    }

    @GetMapping("updateProductCount/{id}/{count}")
    public Result<Integer> updateProductCount(@PathVariable("id") Long id, @PathVariable("count") Integer count){
        log.info("更新商品库存传递的参数为: 商品id:{}, 购买数量:{} ", id, count);
        Integer res = productService.updateProductCount(id, count);
        return new Result<Integer>(HttpCode.SUCCESS, "更新成功", res);
    }
}
