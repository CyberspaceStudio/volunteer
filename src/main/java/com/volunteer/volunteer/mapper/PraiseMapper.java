package com.volunteer.volunteer.mapper;

import com.volunteer.volunteer.model.Praise;

public interface PraiseMapper {
    int deleteByPrimaryKey(Integer praiseId);

    int insert(Praise record);

    int insertSelective(Praise record);

    Praise selectByPrimaryKey(Integer praiseId);

    int updateByPrimaryKeySelective(Praise record);

    int updateByPrimaryKey(Praise record);
}