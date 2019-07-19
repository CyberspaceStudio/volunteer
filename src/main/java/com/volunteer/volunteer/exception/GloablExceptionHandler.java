package com.volunteer.volunteer.exception;

import com.volunteer.volunteer.util.ToolSupport.UniversalResponseBody;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author ：Maolin
 * @className ：GloablExceptionHandler
 * @date ：Created in 2019/7/19 17:45
 * @description： GloablException
 * @version: 1.0
 */
@ControllerAdvice
public class GloablExceptionHandler {
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public UniversalResponseBody handleException(Exception e) {
        String msg = e.getMessage();
        if (msg == null || msg.equals("")) {
            msg = "Server error !";
        }
        return new UniversalResponseBody(-1,msg);
    }
}