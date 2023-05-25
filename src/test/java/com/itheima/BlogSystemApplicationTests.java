package com.itheima;

import com.itheima.web.scheduletask.ScheduleTask;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;



@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogSystemApplicationTests {
    public void contextLoads() {
    }
    @Autowired
    private ScheduleTask scheduleTask;
    @Test
    public void sendSimpleMailTest() {
        scheduleTask.sendEmail();
        System.out.println("邮件发送成功！");
    }
    // 这里只是测试了邮箱，如果要进行邮箱测试的话，必须把ScheduleTask类的第28行注释取消，然后注释掉第27行
}
