package com.volunteer.volunteer.controller;

import com.volunteer.volunteer.service.UserInformationService;
import com.volunteer.volunteer.util.ToolSupport.UniversalResponseBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/index")
public class IndexController {
    @Resource
    private UserInformationService userInformationService;

    @GetMapping("/myInfo")
    public UniversalResponseBody getMyInformation(@RequestParam("mainId") int mainId){

        return new UniversalResponseBody<>(0,"成功",userInformationService.findById(mainId));
    }

    @GetMapping("/othersInfo")
    public UniversalResponseBody getOthersInformation(@RequestParam("mainId")int mainId){
        return new UniversalResponseBody<>(0,"成功",userInformationService.findById(mainId));
    }

    @GetMapping("/")
    public String index(){
        return "/index.html";
    }
}
