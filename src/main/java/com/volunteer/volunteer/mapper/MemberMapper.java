package com.volunteer.volunteer.mapper;

import com.volunteer.volunteer.model.Member;

public interface MemberMapper {
    int deleteByPrimaryKey(Integer wechatId);

    int insert(Member record);

    int insertSelective(Member record);

    Member selectByPrimaryKey(Integer wechatId);

    int updateByPrimaryKeySelective(Member record);

    int updateByPrimaryKey(Member record);
}