package com.volunteer.volunteer.controller;

import com.volunteer.volunteer.service.LikeStrategy;
import com.volunteer.volunteer.util.ToolSupport.UniversalResponseBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class UserLikeController {
    @Resource
    private LikeStrategy likeStrategy;

    @PutMapping("/like")
    public UniversalResponseBody like(int userId,int activityId){
        if(likeStrategy.like(userId,activityId)){
            return new UniversalResponseBody(0,"成功");
        }else{
            return new UniversalResponseBody(-1,"失败");
        }
    }
    @PutMapping("/unlike")
    public UniversalResponseBody unlike(int userId,int activityId){
        if(likeStrategy.unlike(userId,activityId)){
            return new UniversalResponseBody(0,"成功");
        }else{
            return new UniversalResponseBody(-1,"失败");
        }
    }
    @GetMapping("/checkStatus")
    public UniversalResponseBody check(int userId,int activityId){
        if(likeStrategy.checkStatus(userId, activityId)){
            return new UniversalResponseBody<>(0,"成功","exists");
        }else{
            return new UniversalResponseBody<>(0,"成功","empty");
        }
    }
}
