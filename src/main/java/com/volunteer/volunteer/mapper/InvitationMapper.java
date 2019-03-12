package com.volunteer.volunteer.mapper;

import com.volunteer.volunteer.model.Invitation;

public interface InvitationMapper {
    int deleteByPrimaryKey(Integer invitationId);

    int insert(Invitation record);

    int insertSelective(Invitation record);

    Invitation selectByPrimaryKey(Integer invitationId);

    int updateByPrimaryKeySelective(Invitation record);

    int updateByPrimaryKey(Invitation record);
}