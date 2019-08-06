package com.volunteer.volunteer.controller.pcController;


import com.volunteer.volunteer.annotation.UserLoginToken;
import com.volunteer.volunteer.service.FormMssService;
import com.volunteer.volunteer.service.WxPushService;
import com.volunteer.volunteer.util.ToolSupport.UniversalResponseBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 该控制器用于大量独立表单的处理工作
 */
@Slf4j
@RestController
@RequestMapping("/pc/message")
public class FormController {

    @Resource
    private WxPushService wxPushService;

    @UserLoginToken
    @RequestMapping(value = "/send", method = RequestMethod.GET)
    public UniversalResponseBody sendTemplateMessage(
            @RequestParam("time") String time,
            @RequestParam("place") String place,
            @RequestParam("remarks") String remarks
    ) {
        try {
            wxPushService.pushManyUser(time, place, remarks);
            log.info(time+" "+place+" "+remarks);
            return new UniversalResponseBody(0, "success");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("【模板消息】发送失败！", e);
            return new UniversalResponseBody(-1, "failed");
        }
    }
}
