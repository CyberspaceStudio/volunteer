package com.volunteer.volunteer.mapper;

import com.volunteer.volunteer.model.NewMember;

public interface NewMemberMapper {
    int deleteByPrimaryKey(Integer enrollId);

    int insert(NewMember record);

    int insertSelective(NewMember record);

    NewMember selectByPrimaryKey(Integer enrollId);

    int updateByPrimaryKeySelective(NewMember record);

    int updateByPrimaryKey(NewMember record);
}