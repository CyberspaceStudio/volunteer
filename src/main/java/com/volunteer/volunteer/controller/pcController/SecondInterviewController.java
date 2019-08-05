package com.volunteer.volunteer.controller.pcController;

import com.volunteer.volunteer.annotation.UserLoginToken;
import com.volunteer.volunteer.enums.DepartmentEnum;
import com.volunteer.volunteer.model.UserInformation;
import com.volunteer.volunteer.service.EnrollPassService;
import com.volunteer.volunteer.service.EnrollPersonService;
import com.volunteer.volunteer.service.ManagerService;
import com.volunteer.volunteer.service.UserInformationService;
import com.volunteer.volunteer.util.TokenUtil;
import com.volunteer.volunteer.util.ToolSupport.UniversalResponseBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/pc/interview/second")
@Slf4j
public class SecondInterviewController {


    @Resource
    private EnrollPersonService enrollPersonService;

    @Resource
    private EnrollPassService enrollPassService;

    @Resource
    private ManagerService managerService;


    /**
     * @Description: PC端：二面待面试人员
     * @Param: [request]
     * @return: UniversalResponseBody
     */
    @UserLoginToken
    @RequestMapping(value = "/without/{page}", method = RequestMethod.GET)
    public UniversalResponseBody PcWaitSecondInterviewed(@PathVariable("page") int page, HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("token");
        String userName = TokenUtil.getAppUID(token);
        String department = managerService.findManagerByName(userName).getDepartment();

        Map<String, Object> res = enrollPersonService.PcWaitSecondInterviewed(department, page);
        if (res != null) {
            return new UniversalResponseBody<>(0, "success", res);
        } else {
            return new UniversalResponseBody<>(-1, "failed");
        }
    }


    /**
     * @Description: PC端：二面已面试人员
     * @Param: [request]
     * @return: UniversalResponseBody
     */
    @UserLoginToken
    @RequestMapping(value = "/interviewed/{page}", method = RequestMethod.GET)
    public UniversalResponseBody PcSecondInterviewed(@PathVariable("page") int page, HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("token");
        String userName = TokenUtil.getAppUID(token);
        String department = managerService.findManagerByName(userName).getDepartment();

        Map<String, Object> res = enrollPersonService.PcSecondInterviewed(department, page);

        if (res != null) {
            return new UniversalResponseBody<>(0, "success", res);
        } else {
            return new UniversalResponseBody(-1, "failed");
        }
    }


    /**
     * @Description: PC端：二面后录取为部员
     * @Param: [mainIds[], request]
     * @return: UniversalResponseBody
     */
    @UserLoginToken
    @RequestMapping(value = "/member/ADMIT", method = RequestMethod.POST)
    public UniversalResponseBody secondAdmit(@RequestParam("mainIds") int[] mainIds, HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("token");
        String userName = TokenUtil.getAppUID(token);
        String department = managerService.findManagerByName(userName).getDepartment();

        //statusNum, passNum 为0 此时不会进行更新
        try {
            //enrollPassService.ManyPassOrNot(mainIds,"网络技术工作室",0,505);
            enrollPassService.ManyPassOrNot(mainIds, department, 0, 505);
            enrollPersonService.saveManyInformation(mainIds);
            return new UniversalResponseBody(0, "success");
        } catch (Exception e) {
            e.printStackTrace();
            return new UniversalResponseBody(-1, "failed");
        }
    }


    /**
     * @Description: PC端：二面被刷
     * @Param: [mainIds[], request]
     * @return: UniversalResponseBody
     */
    @UserLoginToken
    @RequestMapping(value = "/OUT", method = RequestMethod.POST)
    public UniversalResponseBody secondInterviewNotPass(@RequestParam("mainIds") int[] mainIds, HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("token");
        String userName = TokenUtil.getAppUID(token);
        String department = managerService.findManagerByName(userName).getDepartment();

        //statusNum, passNum 为0 此时不会进行更新
        try {
            enrollPassService.ManyPassOrNot(mainIds, department, 0, 504);
            //enrollPassService.ManyPassOrNot(mainIds,"网络技术工作室",0,504);
            return new UniversalResponseBody(0, "success");
        } catch (Exception e) {
            e.printStackTrace();
            return new UniversalResponseBody(-1, "failed");
        }
    }

}
