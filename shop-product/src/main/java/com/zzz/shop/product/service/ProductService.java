package com.zzz.shop.product.service;

import com.zzz.shop.bean.model.Product;

/**
 * @author zzz
 * 2022-04-27 15:04
 */
public interface ProductService {

    Product getProductById(Long id);

    Integer updateProductCount(Long productId, Integer newCount);
}
