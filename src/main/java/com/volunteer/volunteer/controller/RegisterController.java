package com.volunteer.volunteer.controller;

import com.volunteer.volunteer.model.Manager;
import com.volunteer.volunteer.model.SuperManager;
import com.volunteer.volunteer.service.ManagerService;
import com.volunteer.volunteer.util.ToolSupport.UniversalResponseBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class RegisterController {
    @Resource
    ManagerService managerService;

    @PostMapping("/test2")
    public boolean register(SuperManager superManager){
        return false;
    }

    @GetMapping("/test")
    public UniversalResponseBody register(Manager manager){
        if(managerService.insertManager(manager))
            return new UniversalResponseBody(0,"成功");
        else
            return new UniversalResponseBody(1,"失败");
    }

    @PostMapping("/test3")
    public boolean register(){
        return false;
    }
}
