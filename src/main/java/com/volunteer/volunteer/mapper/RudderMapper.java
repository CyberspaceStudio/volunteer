package com.volunteer.volunteer.mapper;

import com.volunteer.volunteer.model.Rudder;

public interface RudderMapper {
    int deleteByPrimaryKey(Integer rudderId);

    int insert(Rudder record);

    int insertSelective(Rudder record);

    Rudder selectByPrimaryKey(Integer rudderId);

    int updateByPrimaryKeySelective(Rudder record);

    int updateByPrimaryKey(Rudder record);
}