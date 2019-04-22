package com.volunteer.volunteer.util;

import com.volunteer.volunteer.exception.NoAuthenticationException;
import com.volunteer.volunteer.util.ToolSupport.ResponseBodySovler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;

/**
 * Used to solve the weChat open_id and getting session_key
 * 方法更改为返回整个对象getOpenId -->
 */
//@Component
public class WeChatUtil {
    /*@Value("${wx.url}")
    private static String WECHAT_OPENID_URL;
*/
    private static String WECHAT_OPENID_URL = "https://api.weixin.qq.com/sns/jscode2session?appid=wx07995d169ac8092e&secret=1cb93e42d6888bf81c61d0c3ab336696&grant_type=authorization_code&js_code=";

    private static RestTemplate restTemplate = new RestTemplate();

    public static ResponseBodySovler getWechatResponseBody(String code) throws Exception{
        //String url = WECHAT_OPENID_URL + URLEncoder.encode(code,"UTF-8");
        String url = WECHAT_OPENID_URL + code;

        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url,String.class);
        if(responseEntity.getStatusCodeValue() != 200){
            throw new NoAuthenticationException("connect wechat failed");
        }

        ResponseBodySovler responseBodySovler = JsonUtil.jsonToObject(responseEntity.getBody(),ResponseBodySovler.class);
        return responseBodySovler;
    }
}
