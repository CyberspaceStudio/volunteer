package com.volunteer.volunteer.mapper;

import com.volunteer.volunteer.model.UserInformation;

public interface UserInformationMapper {
    int deleteByPrimaryKey(Integer mainId);

    int insert(UserInformation record);

    int insertSelective(UserInformation record);

    UserInformation selectByPrimaryKey(Integer mainId);

    int updateByPrimaryKeySelective(UserInformation record);

    int updateByPrimaryKey(UserInformation record);
}