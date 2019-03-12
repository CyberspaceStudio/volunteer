package com.volunteer.volunteer.mapper;

import com.volunteer.volunteer.model.Form;

public interface FormMapper {
    int deleteByPrimaryKey(Integer formTableId);

    int insert(Form record);

    int insertSelective(Form record);

    Form selectByPrimaryKey(Integer formTableId);

    int updateByPrimaryKeySelective(Form record);

    int updateByPrimaryKey(Form record);
}