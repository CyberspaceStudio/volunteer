package com.volunteer.volunteer.mapper;

import com.volunteer.volunteer.model.UserLike;

import java.util.Map;

public interface UserLikeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserLike record);

    int insertSelective(UserLike record);

    UserLike selectByPrimaryKey(Integer id);

    int getIdCounter(Integer activityId);

    int updateStatus(Map<String,Object> data);

    UserLike getCheckInfo(Map<String,Object> data);

    int updateByPrimaryKeySelective(UserLike record);

    int updateByPrimaryKey(UserLike record);
}