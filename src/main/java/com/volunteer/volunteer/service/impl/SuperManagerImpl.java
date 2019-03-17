package com.volunteer.volunteer.service.impl;

import com.volunteer.volunteer.mapper.SuperManagerMapper;
import com.volunteer.volunteer.model.SuperManager;
import com.volunteer.volunteer.service.SuperManagerService;

import javax.annotation.Resource;

public class SuperManagerImpl implements SuperManagerService {
    @Resource
    private SuperManagerMapper superManagerMapper;

    @Override
    public SuperManager selectSManagerByName(String name) {
        return superManagerMapper.selectByName(name);
    }

    @Override
    public boolean insertSManager(SuperManager superManager) {
        boolean flag = false;
        try{
            superManagerMapper.insert(superManager);
            flag = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }
}
