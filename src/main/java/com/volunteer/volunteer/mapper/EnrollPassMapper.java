package com.volunteer.volunteer.mapper;

import com.volunteer.volunteer.model.EnrollPass;

public interface EnrollPassMapper {
    int deleteByPrimaryKey(Integer passId);

    int insert(EnrollPass record);

    int insertMainId(Integer mainId);

    int insertSelective(EnrollPass record);

    EnrollPass selectByPrimaryKey(Integer passId);

    EnrollPass selectByMainId(Integer mainId);

    int updateByPrimaryKeySelective(EnrollPass record);

    int updateByPrimaryKey(EnrollPass record);

    int updateByMainId(EnrollPass record);

}