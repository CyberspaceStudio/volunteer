package com.volunteer.volunteer.mapper;

import com.volunteer.volunteer.model.FormMss;

import java.util.List;

public interface FormMssMapper {
    int deleteByPrimaryKey(Integer mainId);

    int insert(FormMss record);

    int insertSelective(FormMss record);

    FormMss selectByPrimaryKey(Integer mainId);

    int updateByPrimaryKeySelective(FormMss record);

    int updateByPrimaryKey(FormMss record);

    List<FormMss> selectFormMssByDeadline();
}