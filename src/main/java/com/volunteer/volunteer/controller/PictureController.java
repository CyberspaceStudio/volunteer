package com.volunteer.volunteer.controller;

import com.volunteer.volunteer.util.FileUtil;
import com.volunteer.volunteer.util.ToolSupport.UniversalResponseBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@RestController
public class PictureController {
    @Resource
    private FileUtil fileUtil;

    @PostMapping("/picture/upload")
    public UniversalResponseBody uploadFiles(@RequestParam("file") MultipartFile[] file,@RequestParam("id")int id){
        if(fileUtil.uploadHelper(file,id)){
            return new UniversalResponseBody<>(0,"成功");
        }else{
            return new UniversalResponseBody<>(1,"失败");
        }
    }

}
