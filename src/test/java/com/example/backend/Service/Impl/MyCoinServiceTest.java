package com.example.backend.Service.Impl;

import com.example.backend.BackendApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BackendApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MyCoinServiceTest {

    @Autowired
    private MyCoinService myCoinService;

    @Test
    public void getCoin() {
        HashMap<String, Object> res = myCoinService.getCoin();
        assert res.containsKey("nodes");
        assert res.containsKey("links");
    }
}