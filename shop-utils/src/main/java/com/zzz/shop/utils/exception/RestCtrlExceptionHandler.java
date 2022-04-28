package com.zzz.shop.utils.exception;

import com.zzz.shop.utils.constants.HttpCode;
import com.zzz.shop.utils.resp.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author zzz
 * 2022-04-25 9:31
 */
@RestControllerAdvice
public class RestCtrlExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(RestCtrlExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public Result<String> handleException(Exception e){
        logger.error("服务器抛出了异常：{}", e.getMessage());
        return new Result<>(HttpCode.FAILURE, "服务器异常", e.getMessage());
    }
}
