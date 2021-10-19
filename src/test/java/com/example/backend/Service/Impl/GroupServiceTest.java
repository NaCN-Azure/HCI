package com.example.backend.Service.Impl;

import com.example.backend.BackendApplication;
import com.example.backend.Model.Group;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BackendApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GroupServiceTest {
    @Autowired
    GroupService groupService;

    @Test
    public void getGroupNameList() {
        List<String> res = groupService.getGroupNameList();
        assert !res.isEmpty();

        for (String name : res) {
            System.out.println(name);
        }
    }

    @Test
    public void getGraphByGroupName3Level() {
        String name = "锦江国际集团";
        HashMap<String, Object> res = groupService.getGraphByGroupName3Level(name);
        assert res.containsKey("nodes");
        assert res.containsKey("links");
    }

    @Test
    public void getGroupByNameContains1() {
        String name = "xxxx";
        Group group = groupService.getGroupByNameContains(name);
        assert group == null;
    }

    @Test
    public void getGroupByNameContains2() {
        String name = "锦江国际";
        Group group = groupService.getGroupByNameContains(name);
        assert group != null;

        System.out.println(group.getName());
        assert group.getName().equals("锦江国际集团");
    }

    @Test
    public void getGroupByNameContains3() {
        String name = "锦江国际集团";
        Group group = groupService.getGroupByNameContains(name);
        assert group != null;

        System.out.println(group.getName());
        assert group.getName().equals("锦江国际集团");
    }

    @Test
    public void getGroupById1() {
        long id = -1;
        Group group = groupService.getGroupById(id);
        assert group == null;
    }

    @Test
    public void getGroupById2() {
        long id = 1;
        Group group = groupService.getGroupById(id);
        assert group != null;

        System.out.println(group.getName());
    }

    @Test
    public void getDetailByGroupName1() {
        String name = "xxxxx";
        String res = groupService.getDetailByGroupName(name);
        System.out.println(res);
        assert res.equals("暂无相关信息。");
    }

    @Test
    public void getDetailByGroupName2() {
        String name = "锦江国际集团";
        String res = groupService.getDetailByGroupName(name);
        System.out.println(res);
        assert res.contains(name);
    }
}