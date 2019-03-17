package com.volunteer.volunteer.mapper;

import com.volunteer.volunteer.model.InvitationCode;

public interface InvitationCodeMapper {
    int deleteByPrimaryKey(Integer codeId);

    int insert(InvitationCode record);

    int insertSelective(InvitationCode record);

    InvitationCode selectByPrimaryKey(Integer codeId);

    InvitationCode selectByCode(String code);

    int updateByPrimaryKeySelective(InvitationCode record);

    int updateByPrimaryKey(InvitationCode record);
}