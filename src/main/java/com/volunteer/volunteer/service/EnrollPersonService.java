package com.volunteer.volunteer.service;

import com.volunteer.volunteer.model.EnrollPerson;

import java.sql.ResultSet;
import java.util.Map;

public interface EnrollPersonService {

    boolean insert(EnrollPerson enrollPerson);

    boolean saveInformation(EnrollPerson enrollPerson);

    EnrollPerson findByMainId(Integer mainId);

    boolean updateByDepartment(EnrollPerson enrollPerson,String department,String score,String impression);

    int enrollTotal();

    Map<String,Integer> departmentEnrollTotal();

    Map<String,Integer> crossDepartmentTotal();

}
