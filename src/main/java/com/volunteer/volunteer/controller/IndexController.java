package com.volunteer.volunteer.controller;

import com.volunteer.volunteer.service.UserInformationService;
import com.volunteer.volunteer.util.ToolSupport.UniversalResponseBody;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Controller
public class IndexController {
    @Resource
    private UserInformationService userInformationService;

    @RequestMapping(value = "/index/myInfo",method = RequestMethod.GET)
    @ResponseBody
    public UniversalResponseBody getMyInformation(@RequestParam("mainId") int mainId){

        return new UniversalResponseBody<>(0,"成功",userInformationService.findById(mainId));
    }

    @RequestMapping(value = "/index/othersInfo",method = RequestMethod.GET)
    @ResponseBody
    public UniversalResponseBody getOthersInformation(@RequestParam("mainId")int mainId){
        return new UniversalResponseBody<>(0,"成功",userInformationService.findById(mainId));
    }

/*

    @RequestMapping("/volunteer/index")
    public String index(){
        return "/index.html";
    }

    @RequestMapping("/volunteer")
    public String volunteer(){
        return "/index.html";
    }
*/

}
