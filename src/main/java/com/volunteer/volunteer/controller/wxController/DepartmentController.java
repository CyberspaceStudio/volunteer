package com.volunteer.volunteer.controller.wxController;

import com.volunteer.volunteer.enums.OrganizationEnum;
import com.volunteer.volunteer.model.Department;
import com.volunteer.volunteer.service.DepartmentService;
import com.volunteer.volunteer.util.ToolSupport.UniversalResponseBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
public class DepartmentController {
    @Resource
    DepartmentService departmentService;

    @GetMapping("/organization/info")
    public UniversalResponseBody getDepartmentByOrganization(@RequestParam("organization") String organization){
        List<Department> temp = departmentService.findDepartmentByOrganization(OrganizationEnum.XD.getOrganization());
        List<String> res = new ArrayList<>();
        for (Department row : temp
             ) {
            res.add(row.getDepartmentName());
        }
        return new UniversalResponseBody<>(0,"成功",res);
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
