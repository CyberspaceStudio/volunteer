package com.volunteer.volunteer.controller.pcController;


import com.volunteer.volunteer.annotation.UserLoginToken;
import com.volunteer.volunteer.service.AliSms;
import com.volunteer.volunteer.service.ManagerService;
import com.volunteer.volunteer.util.TokenUtil;
import com.volunteer.volunteer.util.ToolSupport.UniversalResponseBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 该控制器用于大量独立表单的处理工作
 */
@Slf4j
@RestController
@RequestMapping("/pc/message")
public class FormController {

    @Resource
    private AliSms aliSms;

    @Resource
    private ManagerService managerService;

    @UserLoginToken
    @RequestMapping(value = "/send", method = RequestMethod.GET)
    public UniversalResponseBody sendTemplateMessage(
            @RequestParam("time") String time,
            @RequestParam("place") String place,
            @RequestParam("remarks") String remarks, HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("token");
        String userName = TokenUtil.getAppUID(token);
        String department = managerService.findManagerByName(userName).getDepartment();
        try {
            aliSms.sendSmsByDepartment(time, place, remarks,department,"面试");

            return new UniversalResponseBody(0, "success");
        } catch (Exception e) {
            e.printStackTrace();
            log.info("业务异常，发送失败！", e);
            return new UniversalResponseBody(-1, "failed");
        }
    }
}
