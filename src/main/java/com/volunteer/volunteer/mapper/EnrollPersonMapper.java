package com.volunteer.volunteer.mapper;

import com.volunteer.volunteer.model.EnrollPerson;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

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

    List<Map<String,Object>> PcWaitFirstInterview(String department);

    List<Map<String,Object>> PcFirstInterviewed(String department,String score);

    List<Map<String,Object>> crossDepartment(String department);

    List<Map<String,Object>> PcWaitSecondInterviewed(String department,String score);

    List<Map<String,Object>> PcSecondInterviewed(String department,String score);

}