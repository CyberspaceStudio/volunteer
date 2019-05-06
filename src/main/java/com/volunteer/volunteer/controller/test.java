package com.volunteer.volunteer.controller;

import com.volunteer.volunteer.mapper.EnrollPassMapper;
import com.volunteer.volunteer.model.EnrollPerson;
import com.volunteer.volunteer.util.ToolSupport.UniversalResponseBody;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @description: test
 * @author: maolin
 * @create: 2019-05-06 20:19
 **/

@Controller
public class test {

    @Resource
    private EnrollPassMapper enrollPassMapper;
    /**
     * @Description: PC端:跨部人员
     * @Param: [request]
     * @return: UniversalResponseBody<List<EnrollPerson>>
     */
    @GetMapping(value = "/enrollTest")
    @ResponseBody
    public UniversalResponseBody enrollTest(@RequestParam("mainId") int mainId) {
        return new UniversalResponseBody<>(0,"测试",enrollPassMapper.insertMainId(mainId));
    }
}
