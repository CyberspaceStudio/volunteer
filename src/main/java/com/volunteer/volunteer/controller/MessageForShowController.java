package com.volunteer.volunteer.controller;

import com.volunteer.volunteer.model.MessageForShow;
import com.volunteer.volunteer.service.MessageForShowService;
import com.volunteer.volunteer.service.PreviewInfoService;
import com.volunteer.volunteer.util.ToolSupport.UniversalResponseBody;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 该控制用于控制各类圈消息
 */
@RestController
@RequestMapping("/message")
public class MessageForShowController {
    @Resource
    MessageForShowService messageForShowService;

    @Resource
    PreviewInfoService previewInfoService;

    /**
     * 该方法用于创建/上传一条新圈的文本及信息部分
     * @param record 需要前端上传record信息
     * @return 返回该圈的activity_id，以便接下来图片信息上传
     */
    @PostMapping("/new")
    public UniversalResponseBody addNewMessage(MessageForShow record){
        record.setActivityTime(null);
        int res = messageForShowService.addNewMessage(record);
        if(res != -1){
            return new UniversalResponseBody<>(0,"成功",res);
        }else {
            return new UniversalResponseBody(-1,"失败");
        }
    }

    /**
     * 删除一条圈信息
     * @param activityId
     * @param mainId
     * @return
     */
    @DeleteMapping("/remove")
    public  UniversalResponseBody delete(@RequestParam("activityId")int activityId,@RequestParam("mainId")int mainId){
        if (messageForShowService.deleteMessage(activityId,mainId)){
            return new UniversalResponseBody(0,"成功");
        }else{
            return new UniversalResponseBody(-1,"失败");
        }
        // TODO: 2019/4/12 本方法存在缺陷，删除一条圈时应该删除其圈消息，与之关联的图片信息，及服务器端的图片文件
    }

    /**
     * 在圈首页预览展示多条圈消息
     * @param pageNumber 分页信息，当前页数
     * @return 返回信息结果集
     */
    @GetMapping("/index")
    public UniversalResponseBody getPreViewMessage(@RequestParam("pageNumber")int pageNumber){
        return new UniversalResponseBody<>(0,"成功",previewInfoService.getPreviewMassage(pageNumber,7));
    }

    /**
     * 用于展示圈文本信息的细节
     * @param activityId 活动id
     * @return 返回圈信息
     */
    @GetMapping("/detail")
    public UniversalResponseBody getDetailMessage(@RequestParam("activityId")int activityId){
        return new UniversalResponseBody<>(0,"成功",messageForShowService.getMessageDetail(activityId));
    }

    /**
     * 用于个人主页圈信息预览，最多两条预览消息
     * @param mainId 发圈人mainId
     * @return 返回至多两条预览消息
     */
    @GetMapping("/personal/indexPreview")
    public UniversalResponseBody getPersonalPreview(@RequestParam("mainId") int mainId){
        return new UniversalResponseBody<>(0,"成功",previewInfoService.getPreviewByMainId(mainId));
    }


}
