package com.example.backend.Service.Impl;

import com.example.backend.BackendApplication;
import com.example.backend.Model.Rank;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BackendApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RankServiceTest {
    @Autowired
    RankService rankService;

    @Test
    public void getRankByNameContains1() {
        String name = "xxxxx";
        Rank rank = rankService.getRankByNameContains(name);
        assert rank == null;
    }

    @Test
    public void getRankByNameContains2() {
        String name = "低端";
        Rank rank = rankService.getRankByNameContains(name);
        assert rank != null;

        System.out.println(rank.getName());
        assert rank.getName().contains(name);
    }

    @Test
    public void getRankById1() {
        long id = -1;
        Rank rank = rankService.getRankById(id);
        assert rank == null;
    }

    @Test
    public void getRankById2() {
        long id = 1;
        Rank rank = rankService.getRankById(id);
        assert rank != null;

        System.out.println(rank.getName());
    }
}