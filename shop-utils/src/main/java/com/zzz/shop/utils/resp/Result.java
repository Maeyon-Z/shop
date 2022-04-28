package com.zzz.shop.utils.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zzz
 * 2022-04-25 9:31
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {

    private static final long serialVersionUID = 1497405107265595284L;

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 状态描述
     */
    private String codeMsg;

    /**
     *  返回的数据
     */
    private T data;
}
