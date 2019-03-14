package com.volunteer.volunteer.service;

import com.volunteer.volunteer.model.NewMember;

public interface EnrollService {
    /*
        提交新用户信息
     */
    public boolean insertNewsInfo(NewMember newMember);
}
