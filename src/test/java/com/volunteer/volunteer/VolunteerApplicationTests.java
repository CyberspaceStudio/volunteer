package com.volunteer.volunteer;

import com.volunteer.volunteer.mapper.UserInformationMapper;
import com.volunteer.volunteer.model.UserInformation;
import com.volunteer.volunteer.util.RandomUtil;
import com.volunteer.volunteer.util.WeChatUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VolunteerApplicationTests {
    @Autowired
    private WeChatUtil weChatUtil;

    @Resource
    private UserInformationMapper userInformationMapper;
    @Test
    public void contextLoads() {
        System.out.println(RandomUtil.getUniqueKey());
    }

    @Test
    public void test(){


        String openId = "ojA8r5INrv2UanPdLoVsML9tEyOY";
        int mainId = 100011;
/*
        List<UserInformation> list = new ArrayList<>();
        list.add(userInformationMapper.selectByPrimaryKey(mainId));
        System.out.println("测试"+list.toString());*/
        UserInformation userInformation = userInformationMapper.selectByOpenId(openId);
        System.out.println(userInformation.getOpenId());
        System.out.println(userInformation.getFalseName()+"   "+userInformation.getHeadPictureUrl());
    }

    @Test
    public void test2(){
        System.out.println("注入结果："+weChatUtil.test());
    }
}
