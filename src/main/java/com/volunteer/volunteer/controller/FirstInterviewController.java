package com.volunteer.volunteer.controller;

import com.volunteer.volunteer.annotation.UserLoginToken;
import com.volunteer.volunteer.enums.DepartmentEnum;
import com.volunteer.volunteer.service.EnrollPassService;
import com.volunteer.volunteer.service.EnrollPersonService;
import com.volunteer.volunteer.util.ToolSupport.UniversalResponseBody;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/pc/interview/first")
public class FirstInterviewController {

    @Resource
    private EnrollPersonService enrollPersonService;

    @Resource
    private EnrollPassService enrollPassService;


    /**
     * @Description: PC端:一面情况：待面试人员
     * @Param: [request]
     * @return: UniversalResponseBody
     */
    @UserLoginToken
    @GetMapping(value = "/without")
    @ResponseBody
    public UniversalResponseBody PcWaitFirstInterview(@RequestParam("department") int departmentCode) {
        List<Map<String, Object>> res = enrollPersonService.pcWaitFirstInterview(DepartmentEnum.getDepartment(departmentCode));
        if (res != null) {
            return new UniversalResponseBody<>(0, "成功", res);
        } else {
            return new UniversalResponseBody<>(-1, "失败", null);
        }
    }

    /**
     * @Description: PC端:一面情况：已面试人员
     * @Param: [request]
     * @return: UniversalResponseBody
     */
    @UserLoginToken
    @GetMapping(value = "/done")
    @ResponseBody
    public UniversalResponseBody PcFirstInterviewed(@RequestParam("department") int departmentCode) {
        Map<String, List<Map<String, Object>>> res = enrollPersonService.PcFirstInterviewed(DepartmentEnum.getDepartment(departmentCode));
        if (res != null) {
            return new UniversalResponseBody<>(0, "成功", res);
        } else {
            return new UniversalResponseBody<>(-1, "失败", null);
        }
    }

    /**
     * @Description: PC端：一面情况：已面试人员：通过按钮
     * @Param: [mainIds[], request]
     * @return: UniversalResponseBody
     */
    @UserLoginToken
    @PostMapping(value = "/PASS")
    @ResponseBody
    public UniversalResponseBody passFirstInterview(@RequestParam("mainIds") int[] mainIds, @RequestParam("department") int departmentCode) {
        //statusNum, passNum 为0 此时不会进行更新
        try {
            enrollPassService.ManyPassOrNot(mainIds, DepartmentEnum.getDepartment(departmentCode), 100, 501);
            return new UniversalResponseBody(0, "成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new UniversalResponseBody(-1, "失败");
        }
    }

    /**
     * @Description: PC端：一面情况：已面试人员：刷人按钮
     * @Param: [mainIds[], request]
     * @return: UniversalResponseBody
     */
    @UserLoginToken
    @PostMapping(value = "/OUT")
    @ResponseBody
    public UniversalResponseBody notPass(@RequestParam("mainIds") int[] mainIds, @RequestParam("department") int departmentCode) {
        //statusNum, passNum 为0 此时不会进行更新
        try {
            enrollPassService.ManyPassOrNot(mainIds, DepartmentEnum.getDepartment(departmentCode), 500, 0);
            //enrollPassService.ManyPassOrNot(mainIds,"网络技术工作室",500,0);
            return new UniversalResponseBody(0, "成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new UniversalResponseBody(-1, "失败");
        }
    }

    /**
     * @Description: PC端： 一面情况：通过人员
     * @Param: [request]
     * @return: UniversalResponseBody
     */
    @UserLoginToken
    @GetMapping(value = "/interviewed")
    @ResponseBody
    public UniversalResponseBody firstInterviewPass(@RequestParam("department") int departmentCode) {
        List<Map<String, Object>> res = enrollPersonService.firstInterviewPass(DepartmentEnum.getDepartment(departmentCode));
        if (res != null) {
            return new UniversalResponseBody<>(0, "成功", res);
        } else {
            return new UniversalResponseBody<>(-1, "失败", null);
        }
    }


    /**
     * @Description: PC端：一面情况：通过人员：录取为二面按钮
     * @Param: [mainIds[], request]
     * @return: UniversalResponseBody
     */
    @UserLoginToken
    @PostMapping(value = "/second/ADMIT")
    @ResponseBody
    public UniversalResponseBody firstPass(@RequestParam("mainIds") int[] mainIds, @RequestParam("department") int departmentCode) {
        //passNum、statusNum为0代表不更新此数字
        try {
            enrollPassService.ManyPassOrNot(mainIds, DepartmentEnum.getDepartment(departmentCode), 0, 503);
            enrollPassService.ManyUpdateFinalDepartment(mainIds, DepartmentEnum.getDepartment(departmentCode));
            return new UniversalResponseBody(0, "成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new UniversalResponseBody(-1, "失败");
        }
    }

    /**
     * @Description: PC端：一面情况：通过人员：录取为部员按钮
     * @Param: [mainIds[], request]
     * @return: UniversalResponseBody
     */
    @UserLoginToken
    @PostMapping(value = "/member/ADMIT")
    @ResponseBody
    public UniversalResponseBody admitMember(@RequestParam("mainIds") int[] mainIds, @RequestParam("department") int departmentCode) {
        //passNum、statusNum为0代表不更新此数字
        try {
            enrollPassService.ManyPassOrNot(mainIds, DepartmentEnum.getDepartment(departmentCode), 0, 505);
            enrollPassService.ManyUpdateFinalDepartment(mainIds, DepartmentEnum.getDepartment(departmentCode));
            enrollPersonService.saveManyInformation(mainIds);
            return new UniversalResponseBody(0, "成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new UniversalResponseBody(-1, "失败");
        }
    }

    /**
     * @Description: PC端:一面情况：跨部人员
     * @Param: [request]
     * @return: UniversalResponseBody
     */
    @UserLoginToken
    @GetMapping(value = "/cross")
    @ResponseBody
    public UniversalResponseBody crossDepartment(@RequestParam("department") int departmentCode) {
        List<Map<String, Object>> res = enrollPersonService.crossDepartment(DepartmentEnum.getDepartment(departmentCode));
        if (res != null) {
            return new UniversalResponseBody<>(0, "成功", res);
        } else {
            return new UniversalResponseBody<>(-1, "失败", null);
        }
    }

    /**
     * @Description: PC端：跨部协调
     * @Param: [mainId, request]
     * @return: UniversalResponseBody
     */
    @UserLoginToken
    @PostMapping(value = "/transfer")
    @ResponseBody
    public UniversalResponseBody transferDepartment(@RequestParam("mainId") int mainId, @RequestParam("department") int departmentCode) {
        if (enrollPersonService.transferDepartment(mainId, DepartmentEnum.getDepartment(departmentCode))) {
            return new UniversalResponseBody(0, "成功");
        } else {
            return new UniversalResponseBody(-1, "失败");
        }
    }

}
