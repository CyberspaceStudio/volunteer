package com.volunteer.volunteer.mapper;

import com.volunteer.volunteer.model.Manager;

public interface ManagerMapper {
    int deleteByPrimaryKey(Integer manageId);

    int insert(Manager record);

    int insertSelective(Manager record);

    Manager selectByPrimaryKey(Integer manageId);

    int updateByPrimaryKeySelective(Manager record);

    int updateByPrimaryKey(Manager record);
}