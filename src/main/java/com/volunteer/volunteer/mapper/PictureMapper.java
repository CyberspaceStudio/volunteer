package com.volunteer.volunteer.mapper;

import com.volunteer.volunteer.model.Picture;

import java.util.List;

public interface PictureMapper {
    int deleteByPrimaryKey(Integer pictureId);

    int insert(Picture record);

    List<Picture> selectByActivityId(Integer activityId);

    int insertSelective(Picture record);

    Picture selectByPrimaryKey(Integer pictureId);

    int updateByPrimaryKeySelective(Picture record);

    int updateByPrimaryKey(Picture record);
}