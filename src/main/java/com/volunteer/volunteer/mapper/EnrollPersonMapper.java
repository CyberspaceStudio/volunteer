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

    int interviewData();

    int notInterviewData();

    int departmentInterviewData(String department);

    int notDepartmentInterviewData(String department);

    int secondDepartmentInterviewData(String department);

    int notSecondDepartmentInterviewData(String department);

    int departmentEnrollByMan(String department);

    int departmentEnrollByWoman(String department);

}