package com.volunteer.volunteer.controller.pcController;

import com.volunteer.volunteer.model.InvitationCode;
import com.volunteer.volunteer.service.InvitationCodeService;
import com.volunteer.volunteer.util.ToolSupport.UniversalResponseBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 该控制器用于各类邀请码的返回接口的路由工作
 */
@RestController
@RequestMapping("/invitation")
public class InvitationController {
    @Resource
    InvitationCodeService invitationCodeService;

    /**
     * 该方法需要前端构建有关invitationCode 对象的相关字段
     * @param invitationCode invitationCode 实体参数
     * @return 当生成正常邀请码时，会在data字段中返回邀请码
     */
    @PostMapping("/code")
    public UniversalResponseBody invitationCode(InvitationCode invitationCode){
        String code = invitationCodeService.insertInvitationCode(invitationCode);
        if(code.equals("error")){
            return new UniversalResponseBody(1,"failed");
        }else {
            return new UniversalResponseBody<>(0,"成功",code);
        }
    }

    @GetMapping("/check")
    public UniversalResponseBody checkInvitationCode(String invitationCode){
        InvitationCode res = invitationCodeService.checkInvitationCode(invitationCode);
        if(res == null){
            return new UniversalResponseBody<>(-1,"failed","Not exist");
        }else{
            return new UniversalResponseBody<>(0,"成功",res);
        }
    }
}
