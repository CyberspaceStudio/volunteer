package com.volunteer.volunteer.mapper;

import com.volunteer.volunteer.model.UserInformation;

import java.util.List;

public interface UserInformationMapper {

    int deleteByOpenId(String openId);

    int deleteByPrimaryKey(Integer mainId);

    int insert(UserInformation record);

    int insertSelective(UserInformation record);

    UserInformation selectByPrimaryKey(Integer mainId);

    UserInformation selectByOpenId(String openId);

    int updateByPrimaryKeySelective(UserInformation record);

    int updateByPrimaryKey(UserInformation record);

    List<UserInformation> findMembers(String department);
}