package com.volunteer.volunteer.service;

import com.volunteer.volunteer.model.Department;

import java.util.List;

public interface DepartmentService {
    boolean insertNewDepartment(Department department);

    List<Department> findDepartmentByOrganization(String organization);
}
