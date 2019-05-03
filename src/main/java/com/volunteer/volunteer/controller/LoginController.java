package com.volunteer.volunteer.controller;

import com.volunteer.volunteer.dto.WxInfo;
import com.volunteer.volunteer.model.Manager;
import com.volunteer.volunteer.service.ManagerService;
import com.volunteer.volunteer.service.UserInformationService;
import com.volunteer.volunteer.util.ToolSupport.CacheResponseBody;
import com.volunteer.volunteer.util.ToolSupport.UniversalResponseBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;

/**
 * 本控制器用于控制用户登录的多种行为，通过重载方法来区分用户身份
 */
@RestController
@Slf4j
public class LoginController {

    @Resource
    private UserInformationService userInformationService;

    @Resource
    private ManagerService managerService;

    @Cacheable(value = "userCache",key = "#loginData.session_key",condition = "#loginData.session_key !=null")
    @RequestMapping(value = "/login/user", method = RequestMethod.POST)
    public CacheResponseBody login(@NotNull WxInfo loginData) {
        try {
            return userInformationService.userLoginWechat(loginData);
        } catch (Exception e) {
            log.error(" 【微信登录】登录失败", e);
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @Description: pc端登陆
     * @Param: [loginManager, request]
     * @return: UniversalResponseBody
     */
    @RequestMapping(value = "/login/manager", method = RequestMethod.POST)
    public UniversalResponseBody pcLogin(Manager loginManager, HttpServletRequest request) {
        try {
            Manager manager = managerService.findManagerByName(loginManager.getManagerName());
            if (manager == null) {
                return new UniversalResponseBody(0, "用户不存在!");
            } else if (!manager.getManagerPassword().equals(loginManager.getManagerPassword())) {
                return new UniversalResponseBody(-1, "密码错误！");
            } else {
                request.getSession().setAttribute("managerName", manager.getManagerName());
                return new UniversalResponseBody<>(0, "成功！", manager);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("【PC端登陆】登陆失败", e);
            return new UniversalResponseBody(-1, "登陆异常！");
        }
    }
}
