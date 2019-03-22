package com.volunteer.volunteer.controller;

import com.volunteer.volunteer.model.Department;
import com.volunteer.volunteer.service.DepartmentService;
import com.volunteer.volunteer.util.ToolSupport.UniversalResponseBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class DepartmentController {
    @Resource
    DepartmentService departmentService;

    @GetMapping("/organization/info")
    public List<Department> getDepartmentByOrganization(String organization){
        return departmentService.findDepartmentByOrganization(organization);
    }


    @PostMapping("/department/add")
    public UniversalResponseBody addNewDepartmentForAOrganization(Department department){
        if (departmentService.insertNewDepartment(department)){
            return new UniversalResponseBody(0,"成功");
        }else{
            return new UniversalResponseBody(1,"失败");
        }
    }
}
