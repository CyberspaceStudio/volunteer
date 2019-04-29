package com.volunteer.volunteer.service;

import com.volunteer.volunteer.model.UserLike;

public interface LikeStrategy {
    boolean like(int userId,int activityId);

    boolean unlike(int userId,int activityId);

    boolean checkStatus(int userId,int activityId);
}
