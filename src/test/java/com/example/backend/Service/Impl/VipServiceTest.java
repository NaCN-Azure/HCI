package com.example.backend.Service.Impl;

import com.example.backend.BackendApplication;
import com.example.backend.Model.Vip;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BackendApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class VipServiceTest {
    @Autowired
    VipService vipService;

    @Test
    public void getVipByNameContains1() {
        String name = "xxxxx";
        Vip vip = vipService.getVipByNameContains(name);
        assert vip == null;
    }

    @Test
    public void getVipByNameContains2() {
        String name = "锦江蓝卡";
        Vip vip = vipService.getVipByNameContains(name);
        assert vip != null;

        System.out.println(vip.getName());
        assert vip.getName().contains(name);
    }

    @Test
    public void getVipById1() {
        long id = -1;
        Vip vip = vipService.getVipById(id);
        assert vip == null;
    }

    @Test
    public void getVipById2() {
        long id = 1;
        Vip vip = vipService.getVipById(id);
        assert vip != null;

        System.out.println(vip.getName());
    }
}