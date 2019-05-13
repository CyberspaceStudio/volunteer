package com.volunteer.volunteer.service;

import com.volunteer.volunteer.dto.LikerInfo;
import com.volunteer.volunteer.model.UserLike;

import java.util.List;

public interface LikeStrategy {
    boolean like(int userId,int activityId);

    boolean unlike(int userId,int activityId);

    boolean checkStatus(int userId,int activityId);

    int getLikeNumber(int activityId);

    List<LikerInfo> getLikerInfo(int activityId);
}
