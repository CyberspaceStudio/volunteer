package com.volunteer.volunteer.service.impl;

import com.volunteer.volunteer.mapper.ManagerMapper;
import com.volunteer.volunteer.model.Manager;
import com.volunteer.volunteer.service.ManagerService;
import com.volunteer.volunteer.util.ToolSupport.UniversalResponseBody;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ManagerServiceImpl implements ManagerService {
    @Resource
    private ManagerMapper managerMapper;

    @Override
    public boolean insertManager(Manager manager) {
        boolean flag = false;
        try{
            managerMapper.insert(manager);
            flag = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    /**
    *
    @Override
    public UniversalResponseBody findManagerByName(String name) {
    return new UniversalResponseBody(0,"成功",managerMapper.selectByName(name));
    }
     */

    @Override
    public Manager findManagerByName(String name){
        return managerMapper.selectByName(name);
    }
}
