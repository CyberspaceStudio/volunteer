package com.volunteer.volunteer.service;

import com.volunteer.volunteer.dto.WxInfo;
import com.volunteer.volunteer.model.UserInformation;

/**
 * @Author: MaoLin
 * @Date: 2019/3/24 11:04
 * @Version 1.0
 */
public interface UserInformationService {

    UserInformation userLoginWechat(WxInfo loginData) throws Exception;

    UserInformation userLogin(UserInformation user);

    boolean save(UserInformation user);

    UserInformation findById(int mainId);

    UserInformation findByOpenId(String openId);

    boolean deleteById(int mainId);

    boolean deleteByOpenId(String openId);

    boolean update(UserInformation user);
}
