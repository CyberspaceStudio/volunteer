package com.volunteer.volunteer.controller;

import com.volunteer.volunteer.dto.WxInfo;
import com.volunteer.volunteer.model.Manager;
import com.volunteer.volunteer.service.ManagerService;
import com.volunteer.volunteer.service.UserInformationService;
import com.volunteer.volunteer.util.ToolSupport.CacheResponseBody;
import com.volunteer.volunteer.util.ToolSupport.UniversalResponseBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;

/**
 * 本控制器用于控制用户登录的多种行为，通过重载方法来区分用户身份
 * 注:1.可能要序列化接口
 *    2.由于session_key再次请求会更新失效，所以将响应体写在了服务层
 */
@RestController
@Slf4j
public class LoginController {

    @Resource
    private UserInformationService userInformationService;

    @Resource
    private ManagerService managerService;


    @RequestMapping(value = "/login/user",method = RequestMethod.POST)
    public CacheResponseBody login(@NotNull WxInfo loginData) {
        try {
            return userInformationService.userLoginWechat(loginData);
        } catch (Exception e) {
            log.error(" 【微信登录】登录失败",e);
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = "/login/manager",method = RequestMethod.POST)
    public UniversalResponseBody pcLogin(Manager manager, HttpServletResponse response){
        //Manager loginManager = managerService.findManagerByName(manager.getManagerName());
        return null;
    }
}
