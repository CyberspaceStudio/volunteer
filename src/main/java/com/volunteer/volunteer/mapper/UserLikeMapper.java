package com.volunteer.volunteer.mapper;

import com.volunteer.volunteer.model.UserLike;

public interface UserLikeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserLike record);

    int insertSelective(UserLike record);

    UserLike selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserLike record);

    int updateByPrimaryKey(UserLike record);
}