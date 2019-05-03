package com.volunteer.volunteer.mapper;

import com.volunteer.volunteer.model.EnrollPerson;

public interface EnrollPersonMapper {
    int deleteByPrimaryKey(Integer mainId);

    int insert(EnrollPerson record);

    int insertSelective(EnrollPerson record);

    EnrollPerson selectByPrimaryKey(Integer mainId);

    int updateByPrimaryKeySelective(EnrollPerson record);

    int updateByPrimaryKey(EnrollPerson record);

    int enrollTotal();

    int departmentEnrollTotal(String department);

    int crossDepartmentTotal(String department);

}