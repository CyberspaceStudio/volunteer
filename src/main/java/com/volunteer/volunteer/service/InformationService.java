package com.volunteer.volunteer.service;

import com.volunteer.volunteer.model.Member;

public interface InformationService {
    public Member selectMemberByOpenId(String openid);
    public boolean insertMember(Member member);
}
