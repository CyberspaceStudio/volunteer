package com.volunteer.volunteer.controller;

import com.volunteer.volunteer.service.PreviewInfoService;
import com.volunteer.volunteer.util.ToolSupport.UniversalResponseBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

@RestController
@RequestMapping("/memory")
public class DepartmentMemoryController {
    @Resource
    private PreviewInfoService previewInfoService;

    @GetMapping("/myDepartment")
    public UniversalResponseBody getMyDepartmentMemory(@RequestParam("department")String department){
        return new UniversalResponseBody<>(0,"成功",previewInfoService.getPreviewByDepartment(department));
    }
}
