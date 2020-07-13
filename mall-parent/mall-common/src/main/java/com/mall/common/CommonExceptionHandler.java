package com.mall.common;

import com.mall.entity.Result;
import com.mall.entity.StatusCode;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CommonExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Result exceptionHandler(Exception e){
        e.printStackTrace();
        return new Result(false, StatusCode.ERROR,"请求失败！",e.toString());
    }
}
