package com.volunteer.volunteer.mapper;

import com.volunteer.volunteer.model.Department;

import java.util.List;

public interface DepartmentMapper {
    int deleteByPrimaryKey(Integer departmentId);

    int insert(Department record);

    int insertSelective(Department record);

    Department selectByPrimaryKey(Integer departmentId);

    List<Department> selectByOrganization(String organization);

    int updateByPrimaryKeySelective(Department record);

    int updateByPrimaryKey(Department record);
}