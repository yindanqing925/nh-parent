package org.nh.springboot.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.nh.springboot.user.dao.UserDao;
import org.nh.springboot.user.model.User;
import org.nh.springboot.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

/**
 * @author yindanqing
 * @date 2019/5/28 22:42
 * @description DB并发性能测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DbPerfTest {

    private final int qps = 1000;

    private CountDownLatch count = new CountDownLatch(qps);

    @Autowired
    private UserService userService;

    @Test
    public void queryTest() throws InterruptedException {
        for (int i = 0; i < qps ; i++) {
            final int id = i;
            new Thread(() -> {
                count.countDown();
                userService.getUserInfoById(id);
            }).start();
        }
        count.await();
        System.out.println("开始执行");
        Thread.sleep(5 * 1000);
    }

    @Test
    public void modifyTest() throws InterruptedException {
        for (int i = 0; i < qps ; i++) {
            final int id = i;
            new Thread(() -> {
                User user = new User();
                user.setId(id);
                user.setBalance((double) id+3);
                count.countDown();
                try {
                    count.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                userService.modifyUser(user);
            }).start();
        }
        System.out.println("开始执行");
        Thread.sleep(10*1000);
    }

    @Test
    public void insertBatch(){
        ArrayList<User> arrayList = new ArrayList<>();
        int offset = 1000;
        for (int i = 1+ offset; i <= 9000 + offset ; i++) {
            User user = new User();
            user.setId(i);
            user.setUsernname("username" + i);
            user.setPassword("e10adc3949ba59abbe56e057f20f883e");
            user.setBalance((double) i);
            user.setCreated(new Date(System.currentTimeMillis()));
            user.setUpdated(user.getCreated());
            user.setAdress(i);
            user.setPhone(String.valueOf(i));
            user.setTips(user.getPhone());
            arrayList.add(user);
        }
        userService.insertbatch(arrayList);
    }

}
