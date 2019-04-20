package com.volunteer.volunteer.service.impl;

import com.volunteer.volunteer.mapper.UserLikeMapper;
import com.volunteer.volunteer.model.UserLike;
import com.volunteer.volunteer.service.LikeStrategy;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class FirstStrategyImpl implements LikeStrategy {
    @Resource
    UserLikeMapper userLikeMapper;

    @Override
    @Cacheable()
    public void like(UserLike userLike) {

    }

    @Override
    public void unlike(UserLike userLike) {

    }
}
