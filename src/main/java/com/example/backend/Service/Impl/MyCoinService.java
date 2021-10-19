package com.example.backend.Service.Impl;

import com.example.backend.Model.Brand;
import com.example.backend.Model.Group;
import com.example.backend.Model.Rank;
import com.example.backend.Repository.BrandRepository;
import com.example.backend.Repository.GroupRepository;
import com.example.backend.Repository.RankRepository;
import com.example.backend.Service.IMyCoinService;
import com.example.backend.Service.SequenceGeneratorService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class MyCoinService implements IMyCoinService {
    private final BrandRepository brandRepository;

    private final GroupRepository groupRepository;

    private final RankRepository rankRepository;

    public MyCoinService(GroupRepository groupRepository, BrandRepository brandRepository, RankRepository rankRepository, SequenceGeneratorService sequenceGeneratorService) {
        this.groupRepository = groupRepository;
        this.brandRepository = brandRepository;
        this.rankRepository = rankRepository;
    }

    @Override
    public HashMap<String, Object> getCoin() {
        HashMap<String, Object> coin = new HashMap<>();
        List<Object> nodeList = new ArrayList<>();
        List<Object> linkList = new ArrayList<>();

        for (Group group : groupRepository.findAll()) {
            HashMap<String, Object> currNode = new HashMap<>();
            currNode.put("name", group.getName());
            currNode.put("uuid", "group" + group.getId());
            currNode.put("type", "Group");
            currNode.put("color", "rgb(125,213,255)");
            currNode.put("shape", "diamond");
            nodeList.add(currNode);
        }

        for (Brand brand : brandRepository.findAll()) {
            HashMap<String, Object> currNode = new HashMap<>();
            currNode.put("name", brand.getName());
            currNode.put("uuid", "brand" + brand.getId());
            currNode.put("type", "Brand");
            currNode.put("color", "rgb(112,211,189)");
            currNode.put("shape", "circle");
            nodeList.add(currNode);

            HashMap<String, Object> currLink1 = new HashMap<>();
            currLink1.put("sourceid", "group" + brand.getGid());
            currLink1.put("targetid", "brand" + brand.getId());
            currLink1.put("name", "从属于");
            currLink1.put("uuid", "group" + brand.getGid() + "-" + "brand" + brand.getId());
            linkList.add(currLink1);

            HashMap<String, Object> currLink2 = new HashMap<>();
            currLink2.put("sourceid", "rank" + brand.getRid());
            currLink2.put("targetid", "brand" + brand.getId());
            currLink2.put("name", "等级");
            currLink2.put("uuid", "rank" + brand.getRid() + "-" + "brand" + brand.getId());
            linkList.add(currLink2);
        }

        for (Rank rank : rankRepository.findAll()) {
            HashMap<String, Object> currNode = new HashMap<>();
            currNode.put("name", rank.getName());
            currNode.put("uuid", "rank" + rank.getId());
            currNode.put("type", "Rank");
            currNode.put("color", "rgb(255,131,115)");
            currNode.put("shape", "square");
            nodeList.add(currNode);
        }


        coin.put("nodes", nodeList);
        coin.put("links", linkList);
        return coin;
    }
}
