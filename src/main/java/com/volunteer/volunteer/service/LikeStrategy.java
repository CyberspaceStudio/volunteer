package com.volunteer.volunteer.service;

import com.volunteer.volunteer.model.UserLike;

public interface LikeStrategy {
    void like(UserLike userLike);

    void unlike(UserLike userLike);
}
