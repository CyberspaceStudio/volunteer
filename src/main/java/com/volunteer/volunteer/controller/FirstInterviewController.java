package com.volunteer.volunteer.controller;

import com.volunteer.volunteer.annotation.UserLoginToken;
import com.volunteer.volunteer.enums.DepartmentEnum;
import com.volunteer.volunteer.service.EnrollPassService;
import com.volunteer.volunteer.service.EnrollPersonService;
import com.volunteer.volunteer.service.ManagerService;
import com.volunteer.volunteer.util.TokenUtil;
import com.volunteer.volunteer.util.ToolSupport.UniversalResponseBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/pc/interview/first")
public class FirstInterviewController {

    @Resource
    private EnrollPersonService enrollPersonService;

    @Resource
    private EnrollPassService enrollPassService;

    @Resource
    private ManagerService managerService;


    /**
     * @Description: PC端:一面情况：待面试人员
     * @Param: [request]
     * @return: UniversalResponseBody
     */
    @UserLoginToken
    @RequestMapping(value = "/without",method = RequestMethod.GET)
    public UniversalResponseBody PcWaitFirstInterview(HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("token");
        String userName = TokenUtil.getAppUID(token);
        String department = managerService.findManagerByName(userName).getDepartment();

        List<Map<String, Object>> res = enrollPersonService.pcWaitFirstInterview(department);
        if (res != null) {
            return new UniversalResponseBody<>(0, "success", res);
        } else {
            return new UniversalResponseBody<>(-1, "failed");
        }
    }

    /**
     * @Description: PC端:一面情况：已面试人员
     * @Param: [request]
     * @return: UniversalResponseBody
     */
    @UserLoginToken
    @RequestMapping(value = "/done",method = RequestMethod.GET)
    public UniversalResponseBody PcFirstInterviewed(HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("token");
        String userName = TokenUtil.getAppUID(token);
        String department = managerService.findManagerByName(userName).getDepartment();
        //log.info(department);
        Map<String, List<Map<String, Object>>> res = enrollPersonService.PcFirstInterviewed(department);
        if (res != null) {
            return new UniversalResponseBody<>(0, "success", res);
        } else {
            return new UniversalResponseBody<>(-1, "failed");
        }
    }

    /**
     * @Description: PC端：一面情况：已面试人员：通过按钮
     * @Param: [mainIds[], request]
     * @return: UniversalResponseBody
     */
    @UserLoginToken
    @RequestMapping(value = "/PASS",method = RequestMethod.POST)
    public UniversalResponseBody passFirstInterview(@RequestParam("mainIds") int[] mainIds,HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("token");
        String userName = TokenUtil.getAppUID(token);
        String department = managerService.findManagerByName(userName).getDepartment();

        //statusNum, passNum 为0 此时不会进行更新
        try {
            enrollPassService.ManyPassOrNot(mainIds, department, 100, 501);
            return new UniversalResponseBody(0, "success");
        } catch (Exception e) {
            e.printStackTrace();
            return new UniversalResponseBody(-1, "failed");
        }
    }

    /**
     * @Description: PC端：一面情况：已面试人员：刷人按钮
     * @Param: [mainIds[], request]
     * @return: UniversalResponseBody
     */
    @UserLoginToken
    @RequestMapping(value = "/OUT",method = RequestMethod.POST)
    public UniversalResponseBody notPass(@RequestParam("mainIds") int[] mainIds, HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("token");
        String userName = TokenUtil.getAppUID(token);
        String department = managerService.findManagerByName(userName).getDepartment();
        //statusNum, passNum 为0 此时不会进行更新
        try {
            enrollPassService.ManyPassOrNot(mainIds, department, 500, 0);
            //enrollPassService.ManyPassOrNot(mainIds,"网络技术工作室",500,0);
            return new UniversalResponseBody(0, "success");
        } catch (Exception e) {
            e.printStackTrace();
            return new UniversalResponseBody(-1, "failed");
        }
    }

    /**
     * @Description: PC端： 一面情况：通过人员
     * @Param: [request]
     * @return: UniversalResponseBody
     */
    @UserLoginToken
    @RequestMapping(value = "/interviewed",method = RequestMethod.GET)
    public UniversalResponseBody firstInterviewPass(HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("token");
        String userName = TokenUtil.getAppUID(token);
        String department = managerService.findManagerByName(userName).getDepartment();

        List<Map<String, Object>> res = enrollPersonService.firstInterviewPass(department);
        if (res != null) {
            return new UniversalResponseBody<>(0, "success", res);
        } else {
            return new UniversalResponseBody<>(-1, "failed");
        }
    }


    /**
     * @Description: PC端：一面情况：通过人员：录取为二面按钮
     * @Param: [mainIds[], request]
     * @return: UniversalResponseBody
     */
    @UserLoginToken
    @RequestMapping(value = "/second/ADMIT",method = RequestMethod.POST)
    public UniversalResponseBody firstPass(@RequestParam("mainIds") int[] mainIds, HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("token");
        String userName = TokenUtil.getAppUID(token);
        String department = managerService.findManagerByName(userName).getDepartment();

        //passNum、statusNum为0代表不更新此数字
        try {
            enrollPassService.ManyPassOrNot(mainIds, department, 0, 503);
            enrollPassService.ManyUpdateFinalDepartment(mainIds, department);
            return new UniversalResponseBody(0, "success");
        } catch (Exception e) {
            e.printStackTrace();
            return new UniversalResponseBody(-1, "failed");
        }
    }

    /**
     * @Description: PC端：一面情况：通过人员：录取为部员按钮
     * @Param: [mainIds[], request]
     * @return: UniversalResponseBody
     */
    @UserLoginToken
    @RequestMapping(value = "/member/ADMIT",method = RequestMethod.POST)
    public UniversalResponseBody admitMember(@RequestParam("mainIds") int[] mainIds,HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("token");
        String userName = TokenUtil.getAppUID(token);
        String department = managerService.findManagerByName(userName).getDepartment();

        //passNum、statusNum为0代表不更新此数字
        try {
            enrollPassService.ManyPassOrNot(mainIds,department, 0, 505);
            enrollPassService.ManyUpdateFinalDepartment(mainIds, department);
            enrollPersonService.saveManyInformation(mainIds);
            return new UniversalResponseBody(0, "success");
        } catch (Exception e) {
            e.printStackTrace();
            return new UniversalResponseBody(-1, "failed");
        }
    }

    /**
     * @Description: PC端:一面情况：跨部人员
     * @Param: [request]
     * @return: UniversalResponseBody
     */
    @UserLoginToken
    @RequestMapping(value = "/cross",method = RequestMethod.GET)
    public UniversalResponseBody crossDepartment(HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("token");
        String userName = TokenUtil.getAppUID(token);
        String department = managerService.findManagerByName(userName).getDepartment();

        List<Map<String, Object>> res = enrollPersonService.crossDepartment(department);
        if (res != null) {
            return new UniversalResponseBody<>(0, "success", res);
        } else {
            return new UniversalResponseBody<>(-1, "failed");
        }
    }

    /**
     * @Description: PC端：跨部协调
     * @Param: [mainId, request]
     * @return: UniversalResponseBody
     */
    @UserLoginToken
    @RequestMapping(value = "/transfer",method = RequestMethod.POST)
    public UniversalResponseBody transferDepartment(@RequestParam("mainId") int mainId, HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("token");
        String userName = TokenUtil.getAppUID(token);
        String department = managerService.findManagerByName(userName).getDepartment();

        if (enrollPersonService.transferDepartment(mainId,department)) {
            return new UniversalResponseBody(0, "success");
        } else {
            return new UniversalResponseBody(-1, "failed");
        }
    }

}
