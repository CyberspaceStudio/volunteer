package com.volunteer.volunteer.service.impl;

import com.volunteer.volunteer.mapper.UserLikeMapper;
import com.volunteer.volunteer.model.UserLike;
import com.volunteer.volunteer.service.LikeStrategy;
import org.apache.catalina.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service
public class FirstStrategyImpl implements LikeStrategy {
    @Resource
    UserLikeMapper userLikeMapper;

    @Override
    @Transactional
    public boolean like(int userId,int activityId) {
        boolean flag = false;
        if (this.isExists(userId,activityId)){
            try {
                UserLike temp = new UserLike();
                temp.setActivityId(activityId);
                temp.setIsDelete(0);
                temp.setLikeStatus(1);
                temp.setUserId(userId);
                userLikeMapper.insert(temp);
                flag = true;
            }catch (Exception e){
                e.printStackTrace();
            }
        }else{
            try {
                Map<String,Object> data = new HashMap<>();
                data.put("userId",userId);
                data.put("activityId",activityId);
                UserLike temp = userLikeMapper.getCheckInfo(data);
                Map<String,Object> data2 = new HashMap<>();
                data2.put("likeStatus",1);
                data2.put("id",temp.getId());
                userLikeMapper.updateStatus(data2);
                flag = true;
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return flag;
    }

    @Override
    @Transactional
    public boolean unlike(int userId,int activityId) {
        boolean flag = false;
        try {
            Map<String,Object> data = new HashMap<>();
            data.put("userId",userId);
            data.put("activityId",activityId);
            UserLike temp = userLikeMapper.getCheckInfo(data);
            Map<String,Object> data2 = new HashMap<>();
            data2.put("likeStatus",0);
            data2.put("id",temp.getId());
            userLikeMapper.updateStatus(data2);
            flag = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    private boolean isExists(int userId, int activityId){
        UserLike temp = null;
        Map<String,Object> data = new HashMap<>();
        data.put("userId",userId);
        data.put("activityId",activityId);
        temp = userLikeMapper.getCheckInfo(data);
        if(temp != null){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public boolean checkStatus(int userId, int activityId) {
        UserLike temp = null;
        Map<String,Object> data = new HashMap<>();
        data.put("userId",userId);
        data.put("activityId",activityId);
        temp = userLikeMapper.getCheckInfo(data);
        if(temp != null){
            if(temp.getLikeStatus() == 0){
                return false;
            }else{
                return true;
            }
        }else {
            return false;
        }
    }
}
