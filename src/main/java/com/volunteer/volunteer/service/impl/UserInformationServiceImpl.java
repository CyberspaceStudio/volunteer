package com.volunteer.volunteer.service.impl;

import com.volunteer.volunteer.dto.WxInfo;
import com.volunteer.volunteer.mapper.UserInformationMapper;
import com.volunteer.volunteer.model.UserInformation;
import com.volunteer.volunteer.service.UserInformationService;
import com.volunteer.volunteer.util.RandomUtil;
import com.volunteer.volunteer.util.WeChatUtil;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import javax.annotation.Resource;

/**
 * @Author: MaoLin
 * @Date: 2019/3/24 11:15
 * @Version 1.0
 * @Attention：PC端登录还未实现
 */

@Service
@Slf4j
public class UserInformationServiceImpl implements UserInformationService {
    @Resource
    private UserInformationMapper userInformationMapper;


    /**
     * 用户如果未曾使用过，则进行注册
     */
    @Override
    public UserInformation userLoginWechat(WxInfo loginData) throws Exception {
        String openId = WeChatUtil.getOpenId(loginData.getCode());
        UserInformation result = userInformationMapper.selectByOpenId(openId);
        if (result == null) {
            UserInformation res = new UserInformation();
            int mainId = RandomUtil.getUniqueKey();
            while (true) {
                if (userInformationMapper.selectByPrimaryKey(mainId) == null) {
                    res.setMainId(mainId);
                    //result.setMainId(123);
                    log.info("【微信登录】用户第一次使用，进行注册！");
                    break;
                } else {
                    mainId = RandomUtil.getUniqueKey();
                }
            }
            res.setOpenId(openId);
            res.setFalseName(loginData.getFalseName());
            res.setHeadPictureUrl(loginData.getHeadPictureUrl());
            return res;
        }
        return result;
    }

    /**
     * PC端登录，还未实现
     * 可用SpringSecurity实现登录验证
    */
    @Override
    public UserInformation userLogin(UserInformation user) {
        return null;
    }

    @Override
    public boolean save(UserInformation user) {
        return userInformationMapper.insertSelective(user) != 0;
    }

    @Override
    public UserInformation findById(int mainId) {
        return userInformationMapper.selectByPrimaryKey(mainId);
    }

    @Override
    public UserInformation findByOpenId(String openId) {
        return userInformationMapper.selectByOpenId(openId);
    }

    @Override
    public boolean deleteById(int mainId) {
        return userInformationMapper.deleteByPrimaryKey(mainId) != 0;
    }

    @Override
    public boolean deleteByOpenId(String openId) {
        return userInformationMapper.deleteByOpenId(openId) != 0;
    }

    @Override
    public boolean update(UserInformation user) {
        return userInformationMapper.updateByPrimaryKeySelective(user) != 0;
    }
}
