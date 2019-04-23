package com.volunteer.volunteer.controller;

import com.volunteer.volunteer.model.Manager;
import com.volunteer.volunteer.model.SuperManager;
import com.volunteer.volunteer.service.ManagerService;
import com.volunteer.volunteer.service.SuperManagerService;
import com.volunteer.volunteer.util.ToolSupport.UniversalResponseBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 该控制器用于控制多种注册方法，通过mapping重载的方式来使用同类型方法处理不同的注册方式
 */
@RestController
@RequestMapping("/register")
public class RegisterController {
    @Resource
    ManagerService managerService;

    @Resource
    SuperManagerService superManagerService;


    /**
     * 该方法用于注册超级管理员
     * @param superManager 用于注册管理员的辅助对象，通过前端传参来实现，必须和对象字段相同
     * @return 返回一个通用请求体
     */
    @PostMapping("/superadmin")
    public UniversalResponseBody register(SuperManager superManager){
        if (superManagerService.insertSManager(superManager)){
            return new UniversalResponseBody(0,"成功");
        }else {
            return new UniversalResponseBody(1,"失败");
        }
    }

    /**
     * 该方法用于注册普通管理员
     * @param manager 用于管理员注册的辅助对象，前端字段必须和对象属性相同
     * @return 返回一个通用请求体
     */
    @PostMapping("/admin")
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
