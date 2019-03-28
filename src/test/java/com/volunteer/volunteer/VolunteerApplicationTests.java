package com.volunteer.volunteer;

import com.volunteer.volunteer.util.RandomUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VolunteerApplicationTests {

    @Test
    public void contextLoads() {
        System.out.println(RandomUtil.getUniqueKey());
    }

}
