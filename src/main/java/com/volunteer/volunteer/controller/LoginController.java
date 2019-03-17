package com.volunteer.volunteer.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @PostMapping("login/admin")
    public boolean login(){
        return false;
    }
}
