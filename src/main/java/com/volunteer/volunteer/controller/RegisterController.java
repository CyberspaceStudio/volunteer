package com.volunteer.volunteer.controller;

import com.volunteer.volunteer.model.Manager;
import com.volunteer.volunteer.model.SuperManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {
    @PostMapping
    public boolean register(SuperManager superManager){
        return false;
    }

    @PostMapping
    public boolean register(Manager manager){
        return false;
    }

    @PostMapping
    public boolean register(){
        return false;
    }
}
