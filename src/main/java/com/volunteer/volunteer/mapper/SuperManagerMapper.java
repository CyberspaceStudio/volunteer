package com.volunteer.volunteer.mapper;

import com.volunteer.volunteer.model.SuperManager;

public interface SuperManagerMapper {
    int deleteByPrimaryKey(Integer superId);

    SuperManager selectByName(String superName);

    int insert(SuperManager record);

    int insertSelective(SuperManager record);

    SuperManager selectByPrimaryKey(Integer superId);

    int updateByPrimaryKeySelective(SuperManager record);

    int updateByPrimaryKey(SuperManager record);
}