package com.example.backend.Service.Impl;

import com.example.backend.BackendApplication;
import com.example.backend.Model.Privilege;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BackendApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PrivilegeServiceTest {
    @Autowired
    PrivilegeService privilegeService;

    @Test
    public void getPrivilegeByVidAndBid1() {
        long vid = -1, bid = -1;
        Privilege privilege = privilegeService.getPrivilegeByVidAndBid(vid, bid);
        assert privilege == null;
    }

    @Test
    public void getPrivilegeByVidAndBid2() {
        long vid = 1, bid = 1;
        Privilege privilege = privilegeService.getPrivilegeByVidAndBid(vid, bid);
        assert privilege != null;

        System.out.println(privilege.getDiscount());
    }
}