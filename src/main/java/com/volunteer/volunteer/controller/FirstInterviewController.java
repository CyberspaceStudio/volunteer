package com.volunteer.volunteer.controller;

import com.volunteer.volunteer.service.EnrollPassService;
import com.volunteer.volunteer.service.EnrollPersonService;
import com.volunteer.volunteer.service.UserInformationService;
import com.volunteer.volunteer.util.ToolSupport.UniversalResponseBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/enroll")
@Slf4j
public class FirstInterviewController {

    @Resource
    private EnrollPersonService enrollPersonService;

    @Resource
    private EnrollPassService enrollPassService;

    @Resource
    private UserInformationService userInformationService;
    /**
     * @Description: PC端:一面情况：待面试人员
     * @Param: [request]
     * @return: UniversalResponseBody
     */
    @GetMapping(value = "/PcWaitFirstInterview")
    @ResponseBody
    public UniversalResponseBody PcWaitFirstInterview(HttpServletRequest request) {
        List<Map<String, Object>> res = enrollPersonService.pcWaitFirstInterview((String) request.getSession().getAttribute("department"));
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
    @GetMapping(value = "/PcFirstInterviewed")
    @ResponseBody
    public UniversalResponseBody PcFirstInterviewed(HttpServletRequest request) {
        Map<String, List<Map<String, Object>>> res = enrollPersonService.PcFirstInterviewed((String) request.getSession().getAttribute("department"));
        if (res != null) {
            return new UniversalResponseBody<>(0, "成功", res);
        } else {
            return new UniversalResponseBody<>(-1, "失败", null);
        }
    }

    /**
     * @Description: PC端：一面情况：已面试人员：通过
     * @Param: [mainIds[], request]
     * @return: UniversalResponseBody
     */
    @PostMapping(value = "/passFirstInterview")
    @ResponseBody
    public UniversalResponseBody passFirstInterview(@RequestParam("mainIds") int[] mainIds, HttpServletRequest request){
        //statusNum, passNum 为0 此时不会进行更新
        try{
            enrollPassService.ManyPassOrNot(mainIds,(String)request.getSession().getAttribute("department"),100,501);
            return new UniversalResponseBody(0,"成功");
        }catch (Exception e){
            e.printStackTrace();
            return new UniversalResponseBody(-1,"失败");
        }
    }

    /**
     * @Description: PC端：一面情况：已面试人员：刷人
     * @Param: [mainIds[], request]
     * @return: UniversalResponseBody
     */
    @PostMapping(value = "/notPass")
    @ResponseBody
    public UniversalResponseBody notPass(@RequestParam("mainIds") int[] mainIds, HttpServletRequest request){
        //statusNum, passNum 为0 此时不会进行更新
        try{
            enrollPassService.ManyPassOrNot(mainIds,(String)request.getSession().getAttribute("department"),500,0);
            //enrollPassService.ManyPassOrNot(mainIds,"网络技术工作室",500,0);
            return new UniversalResponseBody(0,"成功");
        }catch (Exception e){
            e.printStackTrace();
            return new UniversalResponseBody(-1,"失败");
        }
    }

    /**
     * @Description: PC端： 一面情况：通过人员
     * @Param: [request]
     * @return: UniversalResponseBody
     */
    @GetMapping(value = "/firstInterviewPass")
    @ResponseBody
    public UniversalResponseBody firstInterviewPass(HttpServletRequest request){
        List<Map<String, Object>> res = enrollPersonService.firstInterviewPass((String) request.getSession().getAttribute("department"));
        if (res != null) {
            return new UniversalResponseBody<>(0, "成功", res);
        } else {
            return new UniversalResponseBody<>(-1, "失败", null);
        }
    }


    /**
     * @Description: PC端：一面情况：通过人员：录取为二面
     * @Param: [mainIds[], request]
     * @return: UniversalResponseBody
     */
    @PostMapping(value = "/firstPass")
    @ResponseBody
    public UniversalResponseBody firstPass(@RequestParam("mainIds") int[] mainIds, HttpServletRequest request){
        //passNum、statusNum为0代表不更新此数字
        try{
            enrollPassService.ManyPassOrNot(mainIds,(String)request.getSession().getAttribute("department"),0,503);
            enrollPassService.ManyUpdateFinalDepartment(mainIds,(String)request.getSession().getAttribute("department"));
            return new UniversalResponseBody(0,"成功");
        }catch (Exception e){
            e.printStackTrace();
            return new UniversalResponseBody(-1,"失败");
        }
    }
    /**
     * @Description: PC端：一面情况：通过人员：录取为部员
     * @Param: [mainIds[], request]
     * @return: UniversalResponseBody
     */
    @PostMapping(value = "/admitMember")
    @ResponseBody
    public UniversalResponseBody admitMember(@RequestParam("mainIds") int[] mainIds, HttpServletRequest request){
        //passNum、statusNum为0代表不更新此数字
        try{
            enrollPassService.ManyPassOrNot(mainIds,(String)request.getSession().getAttribute("department"),0,505);
            enrollPassService.ManyUpdateFinalDepartment(mainIds,(String)request.getSession().getAttribute("department"));
            enrollPersonService.saveManyInformation(mainIds);
            return new UniversalResponseBody(0,"成功");
        }catch (Exception e){
            e.printStackTrace();
            return new UniversalResponseBody(-1,"失败");
        }
    }

    /**
     * @Description: PC端:一面情况：跨部人员
     * @Param: [request]
     * @return: UniversalResponseBody
     */
    @GetMapping(value = "/crossDepartment")
    @ResponseBody
    public UniversalResponseBody crossDepartment(HttpServletRequest request) {
        List<Map<String, Object>> res = enrollPersonService.crossDepartment((String) request.getSession().getAttribute("department"));
        if (res != null) {
            return new UniversalResponseBody<>(0, "成功", res);
        } else {
            return new UniversalResponseBody<>(-1, "失败", null);
        }
    }


    /**
     * @Description: PC端：跨部协调
     * @Param: [mainId,request]
     * @return: UniversalResponseBody
     */
    @PostMapping(value = "/transferDepartment")
    @ResponseBody
    public UniversalResponseBody transferDepartment(@RequestParam("mainId") int mainId, HttpServletRequest request){
        if (enrollPersonService.transferDepartment(mainId,(String) request.getSession().getAttribute("department"))) {
            return new UniversalResponseBody(0, "成功");
        } else {
            return new UniversalResponseBody(-1, "失败");
        }
    }

}
