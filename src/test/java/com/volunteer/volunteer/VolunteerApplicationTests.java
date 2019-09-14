package com.volunteer.volunteer;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.volunteer.volunteer.service.AliSms;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;



@RunWith(SpringRunner.class)
@SpringBootTest
public class VolunteerApplicationTests {
    @Resource
    private AliSms aliSms;
    @Test
    public void test(){

    }

}
