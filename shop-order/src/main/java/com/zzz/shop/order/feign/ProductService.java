package com.zzz.shop.order.feign;

import com.zzz.shop.bean.model.Product;
import com.zzz.shop.utils.resp.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author zzz
 * 2022-04-28 19:22
 */
@FeignClient("server-product")
public interface ProductService {
    @GetMapping("/product/get/{id}")
    Product getProduct(@PathVariable("id") Long id);

    @GetMapping("/product/updateProductCount/{id}/{count}")
    Result<Integer> updateProductCount(@PathVariable("id") Long id, @PathVariable("count") Integer count);
}
