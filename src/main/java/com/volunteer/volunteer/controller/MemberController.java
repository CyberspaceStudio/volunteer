package com.volunteer.volunteer.controller;

import com.volunteer.volunteer.annotation.UserLoginToken;
import com.volunteer.volunteer.model.UserInformation;
import com.volunteer.volunteer.service.EnrollPersonService;
import com.volunteer.volunteer.service.ManagerService;
import com.volunteer.volunteer.service.UserInformationService;
import com.volunteer.volunteer.util.ExcelUtil;
import com.volunteer.volunteer.util.Object2Map;
import com.volunteer.volunteer.util.TokenUtil;
import com.volunteer.volunteer.util.ToolSupport.UniversalResponseBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/pc/member")
@Slf4j
public class MemberController {

    @Resource
    private EnrollPersonService enrollPersonService;

    @Resource
    private UserInformationService userInformationService;

    @Resource
    private ManagerService managerService;

    /**
     * @Description: PC端：我的部员
     * @Param: [mainId, request]
     * @return: UniversalResponseBody
     */
    @UserLoginToken
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public UniversalResponseBody myMembers(HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("token");
        //log.info(token);
        String userName = TokenUtil.getAppUID(token);
        //log.info(userName);
        String department = managerService.findManagerByName(userName).getDepartment();
        List<UserInformation> list = userInformationService.findMemberByDepartment(department);
        if (list != null) {
            return new UniversalResponseBody<>(0, "success", list);
        } else {
            return new UniversalResponseBody(-1, "failed");
        }
    }


    /**
     * @Description: PC端：退部
     * @Param: [mainId]
     * @return: UniversalResponseBody
     */
    @UserLoginToken
    @RequestMapping(value = "/DROP", method = RequestMethod.POST)
    public UniversalResponseBody dropOut(int mainId) {
        if (userInformationService.updateDropOut(mainId)) {
            return new UniversalResponseBody<>(0, "success");
        } else {
            return new UniversalResponseBody(-1, "failed");
        }
    }

    /**
     * @Description: 导出部员名单
     * @Param: [response]
     * @return: void
     */
    @UserLoginToken
    @RequestMapping(value = "/export/list", method = RequestMethod.GET)
    public void exportMyMembersList(HttpServletResponse response, HttpServletRequest request) throws IOException {
        String token = request.getHeader("token");
        //log.info(token);
        String userName = TokenUtil.getAppUID(token);
        //log.info(userName);
        String department = managerService.findManagerByName(userName).getDepartment();
        //log.info(department);
        List<Map<String, Object>> listMap = Object2Map.object2MapList(userInformationService.findMemberByDepartment(department));
        ExcelUtil.templateExportExcel(ResourceUtils.getFile("classpath:tempExcel") + "/member.xls", listMap, department + "部员名单.xls", response);

    }


    /**
     * @Description: 导出考勤表
     * @Param: [response]
     * @return: void
     */
    @UserLoginToken
    @RequestMapping(value = "/export/attendance", method = RequestMethod.GET)
    public void exportAttendance(HttpServletResponse response, HttpServletRequest request) throws IOException {
        String token = request.getHeader("token");
        // log.info(token);
        String userName = TokenUtil.getAppUID(token);
        //log.info(userName);
        String department = managerService.findManagerByName(userName).getDepartment();
        List<Map<String, Object>> listMap = Object2Map.object2MapList(userInformationService.findMemberByDepartment(department));
        ExcelUtil.templateExportExcel(
                ResourceUtils.getFile("classpath:tempExcel") + "/attendance.xls", listMap, department + "考勤.xls", response);
    }
}
