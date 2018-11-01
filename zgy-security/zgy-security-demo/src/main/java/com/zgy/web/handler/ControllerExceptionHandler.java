package com.zgy.web.handler;

import com.zgy.exception.UserNotExistException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 定义一个控制器的错误处理器
 */
//@ControllerAdvice //表示这个类里面的方法，都是用来处理啊Controller所抛出来的异常
public class ControllerExceptionHandler {

    @ExceptionHandler(UserNotExistException.class) //处理Controller中抛出UserNotExistException的异常
    @ResponseBody //以json格式返回数据
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) //返回状态码
    public Map<String,Object> handleUserNotExistException(UserNotExistException e){

        Map<String,Object> result = new HashMap<>();
        result.put("id",e.getId());
        result.put("message",e.getMessage());
        return result;
    }
}
