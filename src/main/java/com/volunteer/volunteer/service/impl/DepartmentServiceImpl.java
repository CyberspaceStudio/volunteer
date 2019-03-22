package com.volunteer.volunteer.service.impl;

import com.volunteer.volunteer.mapper.DepartmentMapper;
import com.volunteer.volunteer.model.Department;
import com.volunteer.volunteer.service.DepartmentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Resource
    private DepartmentMapper departmentMapper;

    @Override
    public boolean insertNewDepartment(Department department) {
        boolean flag = false;
        try{
            departmentMapper.insert(department);
            flag = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public List<Department> findDepartmentByOrganization(String organization) {
        return departmentMapper.selectByOrganization(organization);
    }
}
