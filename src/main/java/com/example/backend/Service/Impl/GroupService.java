package com.example.backend.Service.Impl;

import com.example.backend.Model.Brand;
import com.example.backend.Model.Group;
import com.example.backend.Model.Privilege;
import com.example.backend.Repository.BrandRepository;
import com.example.backend.Repository.GroupRepository;
import com.example.backend.Repository.PrivilegeRepository;
import com.example.backend.Service.IGroupService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Service
public class GroupService implements IGroupService {
    private final List<String> brandColorList = Arrays.asList(
            "rgb(230,241,216)",
            "rgb(175,215,136)",
            "rgb(91,189,43)",
            "rgb(72,150,32)",
            "rgb(54,117,23)"
    );

    private final GroupRepository groupRepository;

    private final BrandRepository brandRepository;

    private final PrivilegeRepository privilegeRepository;

    public GroupService(GroupRepository groupRepository, BrandRepository brandRepository, PrivilegeRepository privilegeRepository) {
        this.groupRepository = groupRepository;
        this.brandRepository = brandRepository;
        this.privilegeRepository = privilegeRepository;
    }

    @Override
    public List<String> getGroupNameList() {
        List<String> res = new ArrayList<>();
        for (Group group : groupRepository.findAll()) {
            res.add(group.getName());
        }
        return res;
    }

    @Override
    public HashMap<String, Object> getGraphByGroupName3Level(String groupName) {
        HashMap<String, Object> graph = new HashMap<>();
        List<Object> nodeList = new ArrayList<>();
        List<Object> linkList = new ArrayList<>();

        Group group = groupRepository.findByName(groupName);
        HashMap<String, Object> groupNode = new HashMap<>();
        System.out.println(group.getName());
        groupNode.put("name", group.getName());
        groupNode.put("uuid", "group" + group.getId());
        groupNode.put("type", "Group");
        groupNode.put("color", "rgb(125,213,255)");
        groupNode.put("shape", "piccircle");
        groupNode.put("imgsrc", group.getImgsrc());
        nodeList.add(groupNode);

        for (Brand brand : brandRepository.findAllByGid(group.getId())) {
            HashMap<String, Object> brandNode = new HashMap<>();
            brandNode.put("name", brand.getName());
            brandNode.put("uuid", "brand" + brand.getId());
            brandNode.put("type", "Brand");
            brandNode.put("color", brandColorList.get((int) (brand.getRid() - 1)));
            brandNode.put("shape", "downtriangle");
            brandNode.put("imgsrc", brand.getImgsrc());
            nodeList.add(brandNode);
            HashMap<String, Object> brandLink = new HashMap<>();
            brandLink.put("sourceid", "group" + brand.getGid());
            brandLink.put("targetid", "brand" + brand.getId());
            brandLink.put("uuid", "group" + brand.getGid() + "-" + "brand" + brand.getId());
            linkList.add(brandLink);

            Privilege privilege;
            try {
                privilege = privilegeRepository.findFirstByBidOrderByBreakfastDescCheckoutDesc(brand.getId());
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }

            HashMap<String, Object> breakfastNode = new HashMap<>();
            breakfastNode.put("name", privilege.getBreakfast() + "份");
            breakfastNode.put("uuid", "breakfast" + brand.getId());
            breakfastNode.put("type", "Breakfast");
            breakfastNode.put("shape", "star");
            breakfastNode.put("color", "rgb(551,114,514)");
            nodeList.add(breakfastNode);
            HashMap<String, Object> breakfastLink = new HashMap<>();
            breakfastLink.put("name", "最多免费早餐");
            breakfastLink.put("sourceid", "breakfast" + brand.getId());
            breakfastLink.put("targetid", "brand" + brand.getId());
            breakfastLink.put("uuid", "breakfast" + brand.getId() + "-" + "brand" + brand.getId());
            linkList.add(breakfastLink);

            HashMap<String, Object> checkoutNode = new HashMap<>();
            checkoutNode.put("name", privilege.getCheckout());
            checkoutNode.put("uuid", "checkout" + brand.getId());
            checkoutNode.put("type", "Checkout");
            checkoutNode.put("color", "rgb(127,127,213)");
            checkoutNode.put("shape", "roundrectangle");
            nodeList.add(checkoutNode);
            HashMap<String, Object> checkoutLink = new HashMap<>();
            checkoutLink.put("name", "最晚退房");
            checkoutLink.put("sourceid", "checkout" + brand.getId());
            checkoutLink.put("targetid", "brand" + brand.getId());
            checkoutLink.put("uuid", "checkout" + brand.getId() + "-" + "brand" + brand.getId());
            linkList.add(checkoutLink);
        }

        graph.put("nodes", nodeList);
        graph.put("links", linkList);
        return graph;
    }

    @Override
    public Group getGroupByNameContains(String name) {
        try {
            return groupRepository.findByNameContains(name);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Group getGroupById(long id) {
        try {
            return groupRepository.findById(id);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public String getDetailByGroupName(String groupName) {
        Group group = getGroupByNameContains(groupName);
        if (group == null)
            return "暂无相关信息。";
        return group.getIntroduction() + groupName + "的APP是" + group.getPlatform() + "，官方站点是" + group.getHomepage() + "。";
    }
}
