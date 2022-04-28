package com.zzz.shop.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zzz.shop.bean.model.Product;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author zzz
 * 2022-04-27 15:06
 */
@Repository
public interface ProductMapper extends BaseMapper<Product> {

    Integer updateProductCount(@Param("productId") Long productId,@Param("newCount") Integer newCount);
}
