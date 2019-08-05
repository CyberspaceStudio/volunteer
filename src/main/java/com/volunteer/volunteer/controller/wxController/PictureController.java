package com.volunteer.volunteer.controller.wxController;

import com.volunteer.volunteer.model.Picture;
import com.volunteer.volunteer.service.PictureService;
import com.volunteer.volunteer.util.FileUtil;
import com.volunteer.volunteer.util.ToolSupport.UniversalResponseBody;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/picture")
@CrossOrigin(allowCredentials = "true")
public class PictureController {
    @Resource
    private FileUtil fileUtil;

    @Resource
    private PictureService pictureService;

    @PostMapping("/upload")
    public UniversalResponseBody uploadFiles(@RequestParam("file") MultipartFile[] file,@RequestParam("id")int id){
        if(fileUtil.uploadHelper(file,id)){
            return new UniversalResponseBody<>(0,"成功");
        }else{
            return new UniversalResponseBody<>(1,"失败");
        }
    }
    @GetMapping("/show")
    public UniversalResponseBody getPictureURL(@RequestParam("activityId")int activityId){
        List<Picture> temp = pictureService.getPictureByActivityId(activityId);
        List<String> res = new ArrayList<>();
        for (Picture row: temp) {
            res.add(row.getPictureUrl());
        }
        return new UniversalResponseBody<>(0,"成功",res);
    }
}
