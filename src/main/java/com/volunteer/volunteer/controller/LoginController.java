package com.volunteer.volunteer.controller;

import com.volunteer.volunteer.annotation.UserLoginToken;
import com.volunteer.volunteer.dto.TokenInfo;
import com.volunteer.volunteer.dto.WxInfo;
import com.volunteer.volunteer.model.Manager;
import com.volunteer.volunteer.service.ManagerService;
import com.volunteer.volunteer.service.UserInformationService;
import com.volunteer.volunteer.util.TokenUtil;
import com.volunteer.volunteer.util.ToolSupport.CacheResponseBody;
import com.volunteer.volunteer.util.ToolSupport.UniversalResponseBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.net.http.HttpResponse;

/**
 * 本控制器用于控制用户登录的多种行为，通过重载方法来区分用户身份
 */
@RestController
@Slf4j
@RequestMapping("/login")
public class LoginController {

    @Resource
    private UserInformationService userInformationService;

    @Resource
    private ManagerService managerService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Cacheable(value = "userCache", key = "#loginData.session_key", condition = "#loginData.session_key !=null")
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public CacheResponseBody login(@NotNull WxInfo loginData) {
        try {
            return userInformationService.userLoginWechat(loginData);
        } catch (Exception e) {
            log.error(" 【微信登录】登录失败", e);
            e.printStackTrace();
            return new CacheResponseBody(-1,"失败");
        }
    }

    /**
     * @Description: pc端登陆
     * @Param: [loginManager, request]
     * @return: UniversalResponseBody
     */
    @RequestMapping(value = "/manager", method = RequestMethod.POST)
    public UniversalResponseBody pcLogin(@RequestBody Manager loginData, HttpServletRequest request, HttpServletResponse response) {
        //System.out.println(loginData);
        //System.out.println(request.getHeader("token"));
           //@NotNull @RequestParam("managerName") String managerName, @NotNull @RequestParam("managerPwd") String managerPwd, HttpServletRequest request) {
        try {
            //Manager manager = managerService.findManagerByName(managerName);
            Manager manager = managerService.findManagerByName(loginData.getManagerName());
            if (manager == null) {
                return new UniversalResponseBody(-1,"User does not exist!");
           // } else if (!manager.getManagerPassword().equals(managerPwd)) {
            } else if (!manager.getManagerPassword().equals(loginData.getManagerPassword())) {
                return new UniversalResponseBody(-1, "Wrong password!");
            } else {
                String token = TokenUtil.getToken(manager.getManagerName());
                Cookie cookie=new Cookie("token",token);
                cookie.setPath("/");
                cookie.setMaxAge(60*60*24);
                response.setContentType("text/html;charset=UTF-8");
                response.addCookie(cookie);
                response.addHeader("token",token);
                return new UniversalResponseBody<>(0, "success", manager);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("【PC端登陆】登陆失败", e);
            return new UniversalResponseBody(-1, "failed！");
        }
    }
    @GetMapping("/check/session")
    public UniversalResponseBody checkSession(String session){
        boolean flag = redisTemplate.hasKey("userCache::" + session);
        if(flag){
            return new UniversalResponseBody(0,"success");
        }else {
            log.info("日志信息：访问拦截。提交的session过期" + session);
            return new UniversalResponseBody(102,"failed");
        }
    }

}
