package com.volunteer.volunteer;

import com.volunteer.volunteer.enums.DepartmentEnum;
import com.volunteer.volunteer.mapper.EnrollPersonMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import javax.annotation.Resource;


@RunWith(SpringRunner.class)
@SpringBootTest
public class VolunteerApplicationTests {

    @Test
    public void test(){
        System.out.println(DepartmentEnum.GZS);
    }
}
