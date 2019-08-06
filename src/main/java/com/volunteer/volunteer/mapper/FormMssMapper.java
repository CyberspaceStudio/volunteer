package com.volunteer.volunteer.mapper;

import com.volunteer.volunteer.model.FormMss;

import java.util.List;
import java.util.Map;

public interface FormMssMapper {

    int deleteByPrimaryKey(int mainId);

    int insert(FormMss record);

    int insertSelective(FormMss record);

    FormMss selectByPrimaryKey(int mainId);

    int updateByPrimaryKeySelective(FormMss record);

    int updateByPrimaryKey(FormMss record);

    List<Map<String, Object>> selectFormMssByDeadline();

}