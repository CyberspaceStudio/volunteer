package com.volunteer.volunteer.controller;

import com.volunteer.volunteer.service.EnrollPersonService;
import com.volunteer.volunteer.util.ToolSupport.UniversalResponseBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @description: 报名表控制类
 * @author: maolin
 * @create: 2019-05-03 01:24
 **/

@RestController
@Slf4j
public class EnrollController {

    @Resource
    private EnrollPersonService enrollPersonService;


    /**
    * @Description: 报名总人数
    * @Param: []
    * @return: UniversalResponseBody<Map<String,Integer>>
    */
    @GetMapping(value = "/enrollTotal")
    public UniversalResponseBody<Map<String, Integer>> enrollTotal() {
        Map<String, Integer> res = new HashMap<>();
        res.put("总人数", enrollPersonService.enrollTotal());
        if (res != null) {
            return new UniversalResponseBody<>(0, "成功", res);
        } else
            return new UniversalResponseBody<>(-1, "失败", null);
    }
    /**
     * @Description: 各部门报名人数
     * @Param: []
     * @return: UniversalResponseBody<Map<String,Integer>>
     */
    @GetMapping(value = "/departmentEnrollTotal")
    public UniversalResponseBody<Map<String, Integer>> departmentEnrollTotal() {
        Map<String, Integer> res = enrollPersonService.departmentEnrollTotal();
        if (res != null) {
            return new UniversalResponseBody<>(0, "成功", res);
        } else
            return new UniversalResponseBody<>(-1, "失败", null);
    }


    /**
     * @Description: 跨部门人数
     * @Param: []
     * @return: UniversalResponseBody<Map<String,Integer>>
     */
    @GetMapping(value = "/crossDepartmentTotal")
    public UniversalResponseBody<Map<String, Integer>> crossDepartmentTotal() {
        Map<String, Integer> res = enrollPersonService.crossDepartmentTotal();
        if (res != null) {
            return new UniversalResponseBody<>(0, "成功", res);
        } else
            return new UniversalResponseBody<>(-1, "失败", null);
    }
}
