package com.volunteer.volunteer.controller;

import com.volunteer.volunteer.dto.WxInfo;
import com.volunteer.volunteer.model.UserInformation;
import com.volunteer.volunteer.service.UserInformationService;
import com.volunteer.volunteer.util.ToolSupport.UniversalResponseBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;

/**
 * 本控制器用于控制用户登录的多种行为，通过重载方法来区分用户身份
 */
@RestController
@Slf4j
public class LoginController {

    @Resource
    private UserInformationService userInformationService;

    @PostMapping("login/admin")
    public UniversalResponseBody login(@NotNull WxInfo loginData) {
        try {
            UserInformation user = userInformationService.userLoginWechat(loginData);
            return new UniversalResponseBody<UserInformation>(0, "登陆成功", user);
        } catch (Exception e) {
            log.error("【微信登录】登录失败，e={}", e);
            e.printStackTrace();
            return new UniversalResponseBody(-1, "登录失败");
        }
    }
}
