package com.volunteer.volunteer.util;

import com.volunteer.volunteer.exception.NoAuthenticationException;
import com.volunteer.volunteer.util.ToolSupport.ResponseBodySovler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Used to solve the weChat open_id and getting session_key
 * 方法更改为返回整个对象getOpenId -->
 */
@Component
public class WeChatUtil {

    @Value("${wx.url}")
    private String WECHAT_OPENID_URL;

    private static RestTemplate restTemplate = new RestTemplate();

    public ResponseBodySovler getWechatResponseBody(String code) throws Exception {
        //String url = WECHAT_OPENID_URL + URLEncoder.encode(code,"UTF-8");
        String url = WECHAT_OPENID_URL + code;

        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
        if (responseEntity.getStatusCodeValue() != 200) {
            throw new NoAuthenticationException("connect wechat failed");
        }

        ResponseBodySovler responseBodySovler = JsonUtil.jsonToObject(responseEntity.getBody(), ResponseBodySovler.class);
        return responseBodySovler;
    }

    public String test() {
        //WeChatUtil weChatUtil = new WeChatUtil();
        return WECHAT_OPENID_URL;
    }


}
