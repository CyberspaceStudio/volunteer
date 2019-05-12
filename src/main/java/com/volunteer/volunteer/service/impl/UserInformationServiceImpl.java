package com.volunteer.volunteer.service.impl;

import com.volunteer.volunteer.dto.WxInfo;
import com.volunteer.volunteer.mapper.UserInformationMapper;
import com.volunteer.volunteer.model.UserInformation;
import com.volunteer.volunteer.service.UserInformationService;
import com.volunteer.volunteer.util.ToolSupport.CacheResponseBody;
import com.volunteer.volunteer.util.ToolSupport.ResponseBodySovler;
import com.volunteer.volunteer.util.WeChatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import javax.annotation.Resource;
import java.util.List;


/**
 * @Author: MaoLin
 * @Date: 2019/3/24 11:15
 * @Version 1.0
 * @Attention PC端登录还未实现
 */

@Service
@Slf4j
public class UserInformationServiceImpl implements UserInformationService {
    @Resource
    private UserInformationMapper userInformationMapper;

    @Autowired
    private WeChatUtil weChatUtil;


    /**
     * 用户如果未曾使用过，则进行注册
     * 注：由于session_key再次请求会更新实效，所以将响应体写在了服务层
     * session_key为空时不进行redis缓存，返回数据进行缓存
     * bug:selectByOpenId 查出数据为null，通过测试openId 已经获取。
     */
    @Override
    @CachePut(value = "userCache", key = "#result.session_key",condition = "#result.session_key != null")
    public CacheResponseBody<UserInformation> userLoginWechat(WxInfo loginData) throws Exception {
        ResponseBodySovler wechatResponseBody = weChatUtil.getWechatResponseBody(loginData.getCode());
        UserInformation findResult = userInformationMapper.selectByOpenId(wechatResponseBody.getOpenid());

        if (findResult == null) {
            UserInformation res = new UserInformation();
            res.setOpenId(wechatResponseBody.getOpenid());
            res.setFalseName(loginData.getFalseName());
            res.setHeadPictureUrl(loginData.getHeadPictureUrl());

            log.info("【微信登录】用户第一次使用，进行注册！");

            if (userInformationMapper.insert(res) != 0) {
                return new CacheResponseBody<>(0,wechatResponseBody.getSession_key(),userInformationMapper.selectByOpenId(wechatResponseBody.getOpenid()));

            } else {
                log.error("【数据库操作】插入失败！");
                return new CacheResponseBody<>(1,wechatResponseBody.getSession_key(),null);
            }
        }
        return new CacheResponseBody<>(0,wechatResponseBody.getSession_key(),findResult);
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

    @Override
    public List<UserInformation> findMemberByDepartment(String department){
        return userInformationMapper.findMembers(department);
    }
}
