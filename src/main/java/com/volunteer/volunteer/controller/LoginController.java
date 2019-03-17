package com.volunteer.volunteer.controller;

import com.volunteer.volunteer.util.ToolSupport.UniversalResponseBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 本控制器用于控制用户登录的多种行为，通过重载方法来区分用户身份
 */
@RestController
public class LoginController {
    @PostMapping("login/admin")
    public UniversalResponseBody login(){
        return null;
    }
}
