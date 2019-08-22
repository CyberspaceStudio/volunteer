package com.volunteer.volunteer.service;

import com.volunteer.volunteer.dto.WxInfo;
import com.volunteer.volunteer.model.UserInformation;
import com.volunteer.volunteer.util.ToolSupport.CacheResponseBody;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;
import java.util.Map;

/**
 * @Author: MaoLin
 * @Date: 2019/3/24 11:04
 * @Version 1.0
 */
public interface UserInformationService {

    CacheResponseBody<UserInformation> userLoginWechat(WxInfo loginData) throws Exception;

    UserInformation findById(int mainId);

    UserInformation findByOpenId(String openId);

    boolean updateDropOut(int mainId);

    boolean deleteById(int mainId);

    boolean deleteByOpenId(String openId);

    boolean update(UserInformation user);

    List<UserInformation> findMemberByDepartment(String department);

    Map<String, Object> findMemberByPageAndDepartment(String department, int page);
}
