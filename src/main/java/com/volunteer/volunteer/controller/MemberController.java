package com.volunteer.volunteer.controller;

import com.volunteer.volunteer.enums.DepartmentEnum;
import com.volunteer.volunteer.model.UserInformation;
import com.volunteer.volunteer.service.EnrollPersonService;
import com.volunteer.volunteer.service.UserInformationService;
import com.volunteer.volunteer.util.ExcelUtil;
import com.volunteer.volunteer.util.Object2Map;
import com.volunteer.volunteer.util.ToolSupport.UniversalResponseBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/pc/member")
@Slf4j
public class MemberController {


    @Resource
    private UserInformationService userInformationService;

    /**
     * @Description: PC端：我的部员
     * @Param: [mainId,request]
     * @return: UniversalResponseBody
     */
    @GetMapping(value = "/")
    public UniversalResponseBody myMembers(@RequestParam("department")int departmentCode){
        List<UserInformation> list = userInformationService.findMemberByDepartment(DepartmentEnum.getDepartment(departmentCode));
        if (list != null) {
            return new UniversalResponseBody<>(0, "成功",list);
        } else {
            return new UniversalResponseBody(-1, "失败");
        }
    }


    /**
     * @Description: PC端：退部
     * @Param: [mainId]
     * @return: UniversalResponseBody
     */
    @PostMapping(value = "/DROP")
    public UniversalResponseBody dropOut(int mainId){
        if (userInformationService.updateDropOut(mainId)) {
            return new UniversalResponseBody<>(0, "成功");
        } else {
            return new UniversalResponseBody(-1, "失败");
        }
    }

    /**
     * @Description: 导出部员名单
     * @Param: [response]
     * @return: void
     */
    @GetMapping(value = "/export/list")
    public void exportMyMembersList(HttpServletResponse response,HttpServletRequest request) throws IOException {
        String department = (String)request.getSession().getAttribute("department");
        List<Map<String,Object>> listMap = Object2Map.object2MapList(userInformationService.findMemberByDepartment(department));
        ExcelUtil.templateExportExcel(ResourceUtils.getFile("classpath:tempExcel")+"/member.xls",listMap,department+"部员名单.xls",response);
    }


    @Resource
    private EnrollPersonService enrollPersonService;
    /**
     * @Description: 导出考勤表
     * @Param: [response]
     * @return: void
     */
    @GetMapping(value = "/export/attendance")
    public void exportAttendance(HttpServletResponse response,HttpServletRequest request) throws IOException{
        String department = (String)request.getSession().getAttribute("department");
        List<Map<String,Object>> maps = enrollPersonService.crossDepartment(department);
        ExcelUtil.templateExportExcel(
                ResourceUtils.getFile("classpath:tempExcel")+"/attendance.xls",maps,department+"考勤.xls",response);
    }
}
