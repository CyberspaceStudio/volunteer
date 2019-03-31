package com.volunteer.volunteer.mapper;

import com.volunteer.volunteer.model.MessageForShow;

public interface MessageForShowMapper {
    int deleteByPrimaryKey(Integer activityId);

    int insert(MessageForShow record);

    int insertSelective(MessageForShow record);

    MessageForShow selectByPrimaryKey(Integer activityId);

    int updateByPrimaryKeySelective(MessageForShow record);

    int updateByPrimaryKey(MessageForShow record);
}