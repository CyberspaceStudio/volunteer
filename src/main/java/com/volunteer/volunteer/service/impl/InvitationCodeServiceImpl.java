package com.volunteer.volunteer.service.impl;

import com.volunteer.volunteer.mapper.InvitationCodeMapper;
import com.volunteer.volunteer.model.InvitationCode;
import com.volunteer.volunteer.service.InvitationCodeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class InvitationCodeServiceImpl implements InvitationCodeService {
    @Resource
    private InvitationCodeMapper invitationCodeMapper;

    @Override
    public String insertInvitationCode(InvitationCode invitationCode) {

        String code = generateInvitationCode();
        invitationCode.setCodeContain(code);
        try {
            invitationCodeMapper.insert(invitationCode);
        }catch (Exception e){
            e.printStackTrace();
            code = "error";
        }
        return code;
    }

    /**
     * 该方法用于检查数据库中是否存在该邀请码
     * @param code 传入前端输入的邀请码
     * @return 返回一个存有邀请码注册人信息的对象，如果不存在就返回null
     */
    @Override
    public InvitationCode checkInvitationCode(String code) {
        InvitationCode temp = this.invitationCodeMapper.selectByCode(code);
        if (temp.getCodeContain() != null){
            return temp;
        }
        return null;
    }

    /**
     * 本方法用于通过时间戳，转化其为十六进制的八位伪不重复随机码
     * @return 返回一个八位伪不重复随机码
     */
    @Override
    public String generateInvitationCode() {
        return Integer.toHexString((int)new Date().getTime());
    }
}
