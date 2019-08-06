package com.volunteer.volunteer.service;

import com.alibaba.fastjson.JSON;
import com.volunteer.volunteer.dto.AccessToken;
import com.volunteer.volunteer.dto.TemplateData;
import com.volunteer.volunteer.dto.WxMssDto;
import com.volunteer.volunteer.model.EnrollPerson;
import com.volunteer.volunteer.model.FormMss;
import com.volunteer.volunteer.model.UserInformation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ：Maolin
 * @className ：WxPushService
 * @date ：Created in 2019/7/26 19:04
 * @description： WeChat puch
 * @version: 1.0
 */
@Service
@Slf4j
public class WxPushService {

    private static RestTemplate restTemplate = new RestTemplate();

    @Value("${wx.appid}")
    private String APPID;

    @Value("${wx.secret}")
    private String APPSECRET;

    @Value("${wx.templateId}")
    private String TEMPLATEID;

    @Resource
    private FormMssService formMssService;

    @Resource
    private EnrollPersonService enrollPersonService;

    @Resource
    private UserInformationService userInformationService;

    /**
     * 微信小程序推送单个用户
     */
    public String pushOneUser(String openid, String formId, String name, String departments, String time, String place, String remarks) throws IOException {


        //获取access_token
        String access_token = getAccess_token(APPID, APPSECRET);
        String url = "https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send?access_token=" + access_token;

        //拼接推送的模版
        WxMssDto wxMssDto = new WxMssDto();
        wxMssDto.setTouser(openid);//用户openid
        wxMssDto.setTemplate_id(TEMPLATEID);//模版id
        wxMssDto.setForm_id(formId);//formid

        Map<String, TemplateData> messageData = new HashMap<>();

        //keyword1：姓名，keyword2：心仪部门，keyword3：面试时间，keyword4：面试地点，keyword5：备注
        TemplateData keyword1 = new TemplateData();
        keyword1.setValue(name);
        messageData.put("keyword1", keyword1);


        TemplateData keyword2 = new TemplateData();
        keyword2.setValue(departments);
        messageData.put("keyword2", keyword2);


        TemplateData keyword3 = new TemplateData();
        keyword3.setValue(time);
        messageData.put("keyword3", keyword3);

        TemplateData keyword4 = new TemplateData();
        keyword4.setValue(place);
        messageData.put("keyword4", keyword4);

        TemplateData keyword5 = new TemplateData();
        keyword5.setValue(remarks);
        messageData.put("keyword5", keyword5);

        wxMssDto.setData(messageData);

        ResponseEntity<String> responseEntity =
                restTemplate.postForEntity(url, wxMssDto, String.class);
        log.info("小程序推送结果={}", responseEntity.getBody());
        return responseEntity.getBody();
    }

    /**
     * 微信小程序推送多个用户
     */
    @Transactional
    public void pushManyUser(String time, String place, String remarks) throws Exception {
        List<Map<String, Object>> results = formMssService.findFormMssByDeadline();
        for (Map<String, Object> res : results) {
            int mainId = (int) res.get("main_id");
            UserInformation user = userInformationService.findById(mainId);
            EnrollPerson enrollPerson = enrollPersonService.findByMainId(mainId);
            FormMss formMss = formMssService.findFormMssByMainId(mainId);
            String departments = enrollPerson.getFirstChoice()+" "+enrollPerson.getSecondChoice()+" "+enrollPerson.getThirdChoice();

            log.info(departments+" "+time+" "+place+" "+remarks);

            pushOneUser(user.getOpenId(), formMss.getForm_id(), enrollPerson.getRealName(), departments,time, place, remarks);
        }
    }


    /**
     * 获取access_token
     */
    private String getAccess_token(String appId, String appSecret) throws IOException {
        //获取access_token
        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential" +
                "&appid=" + appId + "&secret=" + appSecret;

        String json = restTemplate.getForObject(url, String.class);
        AccessToken accessToken = JSON.parseObject(json, AccessToken.class);

        return accessToken.getAccess_token();
    }
}