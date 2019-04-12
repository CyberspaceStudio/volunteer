package com.volunteer.volunteer.mapper;

import com.volunteer.volunteer.model.MessageForShow;

import java.util.List;

public interface MessageForShowMapper {
    int deleteByPrimaryKey(Integer activityId);

    int insert(MessageForShow record);

    int insertSelective(MessageForShow record);

    int selectLastId();

    List<MessageForShow> selectByMainId(Integer mainId);

    List<MessageForShow> selectByDepartment(String department);

    MessageForShow selectByPrimaryKey(Integer activityId);

    int updateByPrimaryKeySelective(MessageForShow record);

    int updateByPrimaryKey(MessageForShow record);
}