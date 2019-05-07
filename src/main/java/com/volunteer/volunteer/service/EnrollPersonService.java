package com.volunteer.volunteer.service;

import com.volunteer.volunteer.model.EnrollPerson;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

public interface EnrollPersonService {

    boolean insert(EnrollPerson enrollPerson);

    boolean saveInformation(EnrollPerson enrollPerson);

    EnrollPerson findByMainId(Integer mainId);

    boolean updateScoreAndImpression(Integer mainId,String department,String score,String impression);

    int enrollTotal();

    Map<String,Integer> departmentEnrollTotal();

    Map<String,Integer> crossDepartmentTotal();

    Map<String,Integer> interviewData();

    Map<String, Integer> departmentInterviewData();

    Map<String, Integer> oneDepartmentInterviewData(String department);

    Map<String, Integer> notDepartmentInterviewData();

    Map<String, Integer> secondDepartmentInterviewData(String department);

    Map<String, Integer> departmentEnrollDataBySex(String department);

    Map<String, Integer> oneDepartmentEnrollData(String department);

    List<Map<String,Object>> pcWaitFirstInterview(String department);

    Map<String, List<Map<String,Object>>> PcFirstInterviewed(String department);

    Map<String, List<Map<String,Object>>> PcWaitSecondInterviewed(String department);

    List<Map<String,Object>> crossDepartment(String department);

    Map<String, List<Map<String,Object>>> PcSecondInterviewed(String department);

}
