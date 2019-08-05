package com.volunteer.volunteer.service;

import com.volunteer.volunteer.model.EnrollPerson;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

public interface EnrollPersonService {

    boolean insert(EnrollPerson enrollPerson);

    boolean updateStatusByMainId(Integer mainId,Integer status);

    boolean transferDepartment(int mainId,String department);

    void saveManyInformation(int[] mainIds) throws Exception;

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

    Map<String,Object> pcWaitFirstInterview(String department,int pageNumber);

    //Map<String, Object> PcFirstInterviewed(String department,int pageNumber);
    Map<String, Object> PcFirstInterviewed(String department);

    Map<String,Object> firstInterviewPass(String department,int pageNumber);

    Map<String,Object> PcWaitSecondInterviewed(String department,int pageNumber);

    Map<String,Object> crossDepartment(String department,int pageNumber);

    Map<String,Object> PcSecondInterviewed(String department,int pageNumber);

}
