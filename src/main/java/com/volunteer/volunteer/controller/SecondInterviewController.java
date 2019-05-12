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
public class SecondInterviewController {


    @Resource
    private EnrollPersonService enrollPersonService;

    @Resource
    private EnrollPassService enrollPassService;

    @Resource
    private UserInformationService userInformationService;


    /**
     * @Description: PC端：二面待面试人员
     * @Param: [request]
     * @return: UniversalResponseBody
     */
    @GetMapping(value = "/PcWaitSecondInterviewed")
    @ResponseBody
    public UniversalResponseBody PcWaitSecondInterviewed(HttpServletRequest request) {
        Map<String, List<Map<String, Object>>> res = enrollPersonService.PcWaitSecondInterviewed((String) request.getSession().getAttribute("department"));
        if (res != null) {
            return new UniversalResponseBody<>(0, "请求成功:PC端:二面待面试人员", res);
        } else {
            return new UniversalResponseBody<>(-1, "失败", null);
        }
    }


    /**
     * @Description: PC端：二面已面试人员
     * @Param: [request]
     * @return: UniversalResponseBody
     */
    @GetMapping(value = "/PcSecondInterviewed")
    @ResponseBody
    public UniversalResponseBody PcSecondInterviewed(HttpServletRequest request) {
        Map<String, List<Map<String, Object>>> res = enrollPersonService.PcSecondInterviewed((String) request.getSession().getAttribute("department"));
        if (res != null) {
            return new UniversalResponseBody<>(0, "请求成功:PC端:二面已面试人员", res);
        } else {
            return new UniversalResponseBody(-1, "失败");
        }
    }


    /**
     * @Description: PC端：二面后录取为部员
     * @Param: [mainIds[], request]
     * @return: UniversalResponseBody
     */
    @PostMapping(value = "/secondAdmit")
    @ResponseBody
    public UniversalResponseBody secondAdmit(@RequestParam("mainIds") int[] mainIds, HttpServletRequest request){
        //statusNum, passNum 为0 此时不会进行更新
        try{
            //enrollPassService.ManyPassOrNot(mainIds,"网络技术工作室",0,505);
            enrollPassService.ManyPassOrNot(mainIds,(String)request.getSession().getAttribute("department"),0,505);
            enrollPersonService.saveManyInformation(mainIds);
            return new UniversalResponseBody(0,"成功");
        }catch (Exception e){
            e.printStackTrace();
            return new UniversalResponseBody(-1,"失败");
        }
    }


    /**
     * @Description: PC端：二面被刷
     * @Param: [mainIds[], request]
     * @return: UniversalResponseBody
     */
    @PostMapping(value = "/secondInterviewNotPass")
    @ResponseBody
    public UniversalResponseBody secondInterviewNotPass(@RequestParam("mainIds") int[] mainIds, HttpServletRequest request){
        //statusNum, passNum 为0 此时不会进行更新
        try{
            enrollPassService.ManyPassOrNot(mainIds,(String)request.getSession().getAttribute("department"),0,504);
            //enrollPassService.ManyPassOrNot(mainIds,"网络技术工作室",0,504);
            return new UniversalResponseBody(0,"成功");
        }catch (Exception e){
            e.printStackTrace();
            return new UniversalResponseBody(-1,"失败");
        }
    }

    /**
     * @Description: PC端：二面签到
     * @Param: [mainId, request]
     * @return: UniversalResponseBody
     */
    @PostMapping(value = "/secondInterviewPresence")
    @ResponseBody
    public UniversalResponseBody secondInterviewPresence(@RequestParam("mainId") int mainId){
        try{
            if (enrollPersonService.updateStatusByMainId(mainId,502)) {
                return new UniversalResponseBody(0, "成功");
            }else return new UniversalResponseBody(-1,"失败");
        }catch (Exception e){
            e.printStackTrace();
            return new UniversalResponseBody(-1,"失败");
        }
    }
}
