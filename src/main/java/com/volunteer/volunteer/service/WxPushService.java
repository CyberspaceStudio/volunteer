package com.volunteer.volunteer.service;

import com.volunteer.volunteer.dto.AccessToken;
import com.volunteer.volunteer.dto.TemplateData;
import com.volunteer.volunteer.dto.WxMssDto;
import com.volunteer.volunteer.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
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
//    @Autowired
//    private RestTemplate restTemplate;

    @Value("${wx.appid}")
    private String APPID;

    @Value("${wx.secret}")
    private String APPSECRET;

    @Value("${wx.templateId}")
    private String TEMPLATEID;

    /**
     * 微信小程序推送单个用户
     */
    public String pushOneUser(String appId, String formId) throws IOException {


        //获取access_token
        String access_token = getAccess_token(APPID, APPSECRET);
        String url = "https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send" +
                "?access_token=" + access_token;

        //拼接推送的模版
        WxMssDto wxMssDto = new WxMssDto();
        wxMssDto.setTouser(appId);//用户openid
        wxMssDto.setTemplateId(TEMPLATEID);//模版id
        wxMssDto.setFormId(formId);//formid


        Map<String, TemplateData> m = new HashMap<>(5);

        //keyword1：姓名，keyword2：面试时间，keyword3：面试地点，keyword4：备注
        TemplateData keyword1 = new TemplateData();
        keyword1.setValue("姓名");
        m.put("keyword1", keyword1);

        TemplateData keyword2 = new TemplateData();
        keyword2.setValue("面试时间");
        m.put("keyword2", keyword2);
        wxMssDto.setData(m);

        TemplateData keyword3 = new TemplateData();
        keyword3.setValue("面试地点");
        m.put("keyword3", keyword3);
        wxMssDto.setData(m);

        TemplateData keyword4 = new TemplateData();
        keyword4.setValue("备注");
        m.put("keyword4", keyword4);
        wxMssDto.setData(m);

        ResponseEntity<String> responseEntity =
                restTemplate.postForEntity(url, wxMssDto, String.class);
        log.info("小程序推送结果={}", responseEntity.getBody());
        return responseEntity.getBody();
    }

    /**
     * 获取access_token
     */
    private String getAccess_token(String appId, String appSecret) throws IOException {
        //获取access_token
        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential" +
                "&appid=" + appId + "&secret=" + appSecret;
        String json = restTemplate.getForObject(url, String.class);
        AccessToken accessToken = JsonUtil.jsonToObject(json, AccessToken.class);
        return accessToken.getAccess_token();
    }

}