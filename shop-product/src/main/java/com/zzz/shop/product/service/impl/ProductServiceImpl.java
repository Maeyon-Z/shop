package com.zzz.shop.product.service.impl;

import com.zzz.shop.bean.model.Product;
import com.zzz.shop.product.mapper.ProductMapper;
import com.zzz.shop.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zzz
 * 2022-04-27 15:05
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public Product getProductById(Long id) {
        Product product = productMapper.selectById(id);
        return product;
    }

    @Override
    public Integer updateProductCount(Long productId, Integer count) {
        Integer res = productMapper.updateProductCount(productId, count);
        return null;
    }
}
