package com.volunteer.volunteer.controller;

import com.volunteer.volunteer.model.Department;
import com.volunteer.volunteer.service.DepartmentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class DepartmentController {
    @Resource
    DepartmentService departmentService;

    @GetMapping("/organization/info")
    List<Department> getDepartmentByOrganization(String organization){
        return departmentService.findDepartmentByOrganization(organization);
    }

}
