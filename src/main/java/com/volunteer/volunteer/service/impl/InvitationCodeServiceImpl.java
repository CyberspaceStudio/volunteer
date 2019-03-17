package com.volunteer.volunteer.service.impl;

import com.volunteer.volunteer.mapper.InvitationCodeMapper;
import com.volunteer.volunteer.model.InvitationCode;
import com.volunteer.volunteer.service.InvitationCodeService;

import javax.annotation.Resource;
import java.util.Date;

public class InvitationCodeServiceImpl implements InvitationCodeService {
    @Resource
    private InvitationCodeMapper invitationCodeMapper;

    @Override
    public boolean insertInvitationCode(InvitationCode invitationCode) {
        boolean flag = false;
        String code = generateInvitationCode();
        invitationCode.setCodeContain(code);
        try {
            invitationCodeMapper.insert(invitationCode);
            flag = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public InvitationCode checkInvitationCode(String code) {
        InvitationCode temp = this.invitationCodeMapper.selectByCode(code);
        if (temp.getCodeContain() != null){
            return temp;
        }
        return null;
    }

    @Override
    public String generateInvitationCode() {
        return Integer.toHexString((int)new Date().getTime());
    }
}
