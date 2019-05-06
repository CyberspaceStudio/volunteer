package com.volunteer.volunteer.service.impl;

import com.volunteer.volunteer.mapper.EnrollPassMapper;
import com.volunteer.volunteer.model.EnrollPass;
import com.volunteer.volunteer.service.EnrollPassService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @description: 记录志愿是否通过
 * @author: maolin
 * @create: 2019-05-06 20:30
 **/

@Service
@Slf4j
public class EnrollPassServiceImpl implements EnrollPassService {

    @Resource
    private EnrollPassMapper enrollPassMapper;


    /**
    * @Description: 报名第一次插入信息，mainId插入
    * @Param: [mainId]
    * @return: boolean
    */
    @Override
    public boolean insertMainId(Integer mainId){
        return enrollPassMapper.insertMainId(mainId)>0;
    }

    /**
    * @Description: 一面是否通过 更新操作
    * @Param: [enrollPass]
    * @return: boolean
    */
    @Override
    public boolean PassOrNot(EnrollPass enrollPass){
        return enrollPassMapper.updateByMainId(enrollPass)>0;
    }

}
