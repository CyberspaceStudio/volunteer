package com.volunteer.volunteer.service;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.volunteer.volunteer.dto.AliSmsInfo;
import com.volunteer.volunteer.mapper.EnrollPersonMapper;
import com.volunteer.volunteer.model.EnrollPerson;
import com.volunteer.volunteer.util.ToolSupport.UniversalResponseBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ：Maolin
 * @className ：AliSms
 * @date ：Created in 2019/9/14 8:53
 * @description： 阿里云短信发送
 * @version: 1.0
 */
@Service
@Slf4j
public class AliSms {

    /**
     * 短信模板
     */
    @Value("${aliyun.templateCode}")
    private String TEMPLATE_CODE;

    @Value("${aliyun.signName}")
    private String SIGN_NAME;

    @Value("${aliyun.accessKeyId}")
    private String ACCESSKEY_ID;

    @Value("${aliyun.accessKeySecret}")
    private String ACCESS_KEY_SECRET;

    @Resource
    private EnrollPersonMapper enrollPersonMapper;

    /**
     * 产品名称:云通信短信API产品,开发者无需替换
     */
    static final String product = "Dysmsapi";
    /**
     * 产品域名,开发者无需替换
     */
    static final String domain = "dysmsapi.aliyuncs.com";


    public SendSmsResponse sendSms(AliSmsInfo aliSmsInfo,String receiveTelNo) throws ClientException {

        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        //初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", ACCESSKEY_ID, ACCESS_KEY_SECRET);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);

        //组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();

        //必填:待发送手机号
        request.setPhoneNumbers(receiveTelNo);

        //必填:短信签名-可在短信控制台中找到
        request.setSignName(SIGN_NAME);

        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode(TEMPLATE_CODE);


        //name department activity timeSlot place telNo
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        request.setTemplateParam(JSONObject.toJSONString(aliSmsInfo));


        //选填-上行短信扩展码(无特殊需求用户请忽略此字段)
        //request.setSmsUpExtendCode("90997");

        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        //request.setOutId("yourOutId");

        //hint 此处可能会抛出异常，注意catch
        SendSmsResponse sendSmsResponse = null;
        try {
            sendSmsResponse = acsClient.getAcsResponse(request);
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return sendSmsResponse;
    }

    @Transactional
    public void sendSmsByDepartment(String time, String place, String remarks, String department,String activity) throws ClientException {
        List<EnrollPerson> res = enrollPersonMapper.findEnrollPersonByDepartment(department);
        for (EnrollPerson  person: res){
            AliSmsInfo aliSmsInfo = new AliSmsInfo(person.getRealName(),department,activity,time,place,remarks);
            SendSmsResponse sendSmsResponse = sendSms(aliSmsInfo,person.getTelNo());
            log.info("code:"+sendSmsResponse.getCode()+",message:"+sendSmsResponse.getMessage());
        }
    }
}