package com.volunteer.volunteer.controller;


import com.volunteer.volunteer.model.EnrollPerson;
import com.volunteer.volunteer.model.UserInformation;
import com.volunteer.volunteer.service.EnrollPassService;
import com.volunteer.volunteer.service.EnrollPersonService;
import com.volunteer.volunteer.service.UserInformationService;
import com.volunteer.volunteer.util.ToolSupport.UniversalResponseBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @description: 报名表控制类
 * @author: maolin
 * @create: 2019-05-03 01:24
 **/

@Controller
@RequestMapping("/enroll")
@Slf4j
public class EnrollController {

    @Resource
    private EnrollPersonService enrollPersonService;

    @Resource
    private EnrollPassService enrollPassService;

    @Resource
    private UserInformationService userInformationService;

    /**
     * @Description: 报名
     * @Param: []
     * @return: UniversalResponseBody<Map < String, Integer>>
     */
    @PostMapping(value = "/index")
    @ResponseBody
    public UniversalResponseBody<EnrollPerson> enroll(
            @NotNull @RequestParam("mainId") int mainId,
            @NotNull @RequestParam("realName") String realName,
            @NotNull @RequestParam("sex") String sex,
            @NotNull @RequestParam("telNo") String telNo,
            @NotNull @RequestParam("wechat") String wechat,
            @NotNull @RequestParam("school") String school,
            @NotNull @RequestParam("organization") String organization,
            @NotNull @RequestParam("firstChoice") String firstChoice,
            @RequestParam("secondChoice") String secondChoice,
            @RequestParam("thirdChoice") String thirdChoice,
            @RequestParam("introduction") String introduction
    ) {
        EnrollPerson res = new EnrollPerson();
        res.setMainId(mainId);
        res.setRealName(realName);
        res.setSex(sex);
        res.setTelNo(telNo);
        res.setWechat(wechat);
        res.setSchool(school);
        res.setOrganization(organization);
        res.setFirstChoice(firstChoice);
        res.setSecondChoice(secondChoice);
        res.setThirdChoice(thirdChoice);
        res.setIntroduction(introduction);
        res.setEnrollStatus("0");
        try {
            if (enrollPersonService.insert(res) && enrollPassService.insertMainId(mainId)) {
                return new UniversalResponseBody<>(0, "成功", res);
            } else {
                return new UniversalResponseBody<>(-1, "数据插入失败", res);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new UniversalResponseBody<>(-1, "失败", null);
        }
    }

    /**
     * @Description: 报名总人数
     * @Param: []
     * @return: UniversalResponseBody<Map < String, Integer>>
     */
    @GetMapping(value = "/enrollTotal")
    @ResponseBody
    public UniversalResponseBody<Map<String, Integer>> enrollTotal() {
        Map<String, Integer> res = new TreeMap<>();
        int total = enrollPersonService.enrollTotal();

        if (total >= 0) {
            res.put("总人数", total);
            return new UniversalResponseBody<>(0, "请求成功:报名总人数", res);
        } else
            return new UniversalResponseBody<>(-1, "失败", null);
    }


    /**
     * @Description: 各部门报名数据
     * @Param: []
     * @return: UniversalResponseBody<Map < String, Map < String, Integer>>>
     */
    @GetMapping(value = "/departmentEnrollData")
    @ResponseBody
    public UniversalResponseBody<Map<String, Map<String, Integer>>> departmentEnrollData() {
        Map<String, Integer> res1 = enrollPersonService.departmentEnrollTotal();
        Map<String, Integer> res2 = enrollPersonService.crossDepartmentTotal();
        Map<String, Map<String, Integer>> res = new TreeMap<>();

        if (res1 != null && res2 != null) {
            res.put("各部门报名人数", res1);
            res.put("各部门跨部人数", res2);
            return new UniversalResponseBody<>(0, "请求成功:各部门报名数据", res);
        } else
            return new UniversalResponseBody<>(-1, "失败", null);
    }


    /**
     * @Description: 总队面试数据
     * @Param: []
     * @return: UniversalResponseBody<Map < String, Integer>>
     */
    @GetMapping(value = "/interviewData")
    @ResponseBody
    public UniversalResponseBody<Map<String, Integer>> interviewData() {
        Map<String, Integer> res = enrollPersonService.interviewData();
        if (res != null) {
            return new UniversalResponseBody<>(0, "请求成功:总队已面试和未面试人数", res);
        } else
            return new UniversalResponseBody<>(-1, "失败", null);
    }

    /**
     * @Description: 各部门面试数据
     * @Param: []
     * @return: UniversalResponseBody<Map < String, Integer>>
     */
    @GetMapping(value = "/departmentInterviewData")
    @ResponseBody
    public UniversalResponseBody<Map<String, Map<String, Integer>>> departmentInterviewData() {
        Map<String, Integer> res1 = enrollPersonService.departmentInterviewData();
        Map<String, Integer> res2 = enrollPersonService.notDepartmentInterviewData();
        Map<String, Map<String, Integer>> res = new TreeMap<>();
        if (res1 != null && res2 != null) {
            res.put("各部门已面试人数", res1);
            res.put("各部门未面试人数", res2);

            return new UniversalResponseBody<>(0, "请求成功:各部门面试数据", res);
        } else
            return new UniversalResponseBody<>(-1, "失败", null);
    }


    /**
     * @Description: 单个部门一面数据
     * @Param: []
     * @return: UniversalResponseBody<Map < String, Integer>>
     */
    @GetMapping(value = "/oneDepartmentInterviewData")
    @ResponseBody
    public UniversalResponseBody<Map<String, Integer>> oneDepartmentInterviewData(@NotNull @RequestParam("department") String department) {

        Map<String, Integer> res = enrollPersonService.oneDepartmentInterviewData(department);
        if (res != null) {
            return new UniversalResponseBody<>(0, "请求成功:单个部门一面数据", res);
        } else
            return new UniversalResponseBody<>(-1, "失败", null);
    }


    /**
     * @Description: 单个部门二面面试数据
     * @Param: []
     * @return: UniversalResponseBody<Map < String, Integer>>
     */
    @GetMapping(value = "/secondDepartmentInterviewData")
    @ResponseBody
    public UniversalResponseBody<Map<String, Integer>> secondDepartmentInterviewData(@NotNull @RequestParam("department") String department) {

        Map<String, Integer> res = enrollPersonService.secondDepartmentInterviewData(department);
        if (res != null) {
            return new UniversalResponseBody<>(0, "请求成功:单个部门二面数据", res);
        } else
            return new UniversalResponseBody<>(-1, "失败", null);
    }

    /**
     * @Description: 查看新生简历/一面扫码签到
     * @Param: []
     * @return: UniversalResponseBody<EnrollPerson>
     */
    @PostMapping(value = "/notDepartmentInterviewData")
    @ResponseBody
    public UniversalResponseBody<EnrollPerson> checkResume(Integer mainId) {
        EnrollPerson res = enrollPersonService.findByMainId(mainId);
        if (res != null) {
            return new UniversalResponseBody<>(0, "成功", res);
        } else
            return new UniversalResponseBody<>(-1, "未报名或数据错误", null);
    }


    /**
     * @Description: 一面打分
     * @Param: [mainId, department, score, impression]
     * @return: UniversalResponseBody<EnrollPerson>
     */
    @PostMapping(value = "/updateScoreAndImpression")
    @ResponseBody
    public UniversalResponseBody updateScoreAndImpression(
            @NotNull @RequestParam("mainId") Integer mainId,
            @NotNull @RequestParam("department") String department,
            @NotNull @RequestParam("score") String score,
            @RequestParam("impression") String impression) {

        if (enrollPersonService.updateScoreAndImpression(mainId, department, score, impression)) {
            return new UniversalResponseBody(0, "成功");
        } else
            return new UniversalResponseBody(-1, "失败");
    }

    /**
     * @Description: 单个部门招新数据
     * @Param: [department]
     * @return: UniversalResponseBody<Map < String, Integer>>
     */
    @GetMapping(value = "/oneDepartmentEnrollData")
    @ResponseBody
    public UniversalResponseBody<Map<String, Integer>> oneDepartmentEnrollData(@NotNull @RequestParam("department") String department) {
        Map<String, Integer> res = new TreeMap<>();
        Map<String, Integer> res1 = enrollPersonService.oneDepartmentEnrollData(department);
        Map<String, Integer> res2 = enrollPersonService.departmentEnrollDataBySex(department);

        if (res1 != null && res2 != null) {
            res.putAll(res1);
            res.putAll(res2);
            return new UniversalResponseBody<>(0, "请求成功:部门招新数据", res);
        } else
            return new UniversalResponseBody<>(-1, "请求失败", null);
    }


    /**
     * @Description: PC端：我的部员
     * @Param: [mainId,request]
     * @return: UniversalResponseBody
     */
    @GetMapping(value = "/myMembers")
    @ResponseBody
    public UniversalResponseBody myMembers(HttpServletRequest request){
        List<UserInformation> list = userInformationService.findMemberByDepartment((String) request.getSession().getAttribute("department"));
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
    @PostMapping(value = "/dropOut")
    @ResponseBody
    public UniversalResponseBody dropOut(int mainId){
        if (userInformationService.updateDropOut(mainId)) {
            return new UniversalResponseBody<>(0, "成功");
        } else {
            return new UniversalResponseBody(-1, "失败");
        }
    }




}
