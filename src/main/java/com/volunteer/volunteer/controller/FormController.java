package com.volunteer.volunteer.controller;


import com.volunteer.volunteer.annotation.UserLoginToken;
import com.volunteer.volunteer.util.ToolSupport.UniversalResponseBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 该控制器用于大量独立表单的处理工作
 */
@Slf4j
@RestController
@RequestMapping("/pc/message")
public class FormController {

    //@UserLoginToken
    @RequestMapping(value = "/send",method = RequestMethod.GET)
    public UniversalResponseBody sendTemplateMessage(){
        //TODO
        return null;
    }

}
