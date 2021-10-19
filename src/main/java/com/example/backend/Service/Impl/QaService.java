package com.example.backend.Service.Impl;

import com.example.backend.Model.*;
import com.example.backend.Repository.BrandRepository;
import com.example.backend.Repository.PrivilegeRepository;
import com.example.backend.Service.IQaService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QaService implements IQaService {
    private final GroupService groupService;

    private final BrandService brandService;

    private final RankService rankService;

    private final VipService vipService;

    private final PrivilegeService privilegeService;

    private final BrandRepository brandRepository;

    private final PrivilegeRepository privilegeRepository;

    public QaService(GroupService groupService, BrandService brandService, RankService rankService, VipService vipService, PrivilegeService privilegeService, BrandRepository brandRepository, PrivilegeRepository privilegeRepository) {
        this.groupService = groupService;
        this.brandService = brandService;
        this.rankService = rankService;
        this.vipService = vipService;
        this.privilegeService = privilegeService;
        this.brandRepository = brandRepository;
        this.privilegeRepository = privilegeRepository;
    }

    @Override
    public String getAnswer(int questionIndex, String groupName, String brandName, String rankName, String vipName) {
        Group group = groupService.getGroupByNameContains(groupName);
        Brand brand = brandService.getBrandByNameContains(brandName);
        Rank rank = rankService.getRankByNameContains(rankName);
        Vip vip = vipService.getVipByNameContains(vipName);

        System.out.println("question: q" + (questionIndex - 1));
        System.out.println(group);
        System.out.println(brand);
        System.out.println(rank);
        System.out.println(vip);

        if (questionIndex == 1)
            return getGroupIntroAnswer(group);
        if (questionIndex == 2)
            return getBrandsUnderGroupAnswer(group);
        if (questionIndex == 3)
            return getRelationBetweenBrandAndGroup(brand, group);
        if (questionIndex == 4)
            return getGroupByBrandAnswer(brand);
        if (questionIndex == 5)
            return getRankByBrandAnswer(brand);
        if (questionIndex == 6)
            return getBrandsByRankAnswer(rank);
        if (questionIndex == 7)
            return getBrandsByGroupAndRankAnswer(group, rank, 8);
        if (questionIndex == 8)
            return getAppAndPlatByBrandAnswer(brand);
        if (questionIndex == 9)
            return getDiscountByBrandAndVipAnswer(brand, vip);
        if (questionIndex == 10)
            return getPrivilegeAnswerByBrandAndVipAnswer(brand, vip);
        if (questionIndex == 11)
            return getBreakfastDetailByBrandAnswer(brand);
        if (questionIndex == 12)
            return getCheckoutDetailByBrandAnswer(brand);


        return "...";
    }


    @Override
    public String getGroupIntroAnswer(Group group) {
        if (group == null)
            return "没有找到这个集团的简介。";
        return group.getIntroduction();
    }

    @Override
    public String getBrandsUnderGroupAnswer(Group group) {
        if (group == null)
            return "没有找到这个集团的信息。";
        StringBuilder res = new StringBuilder();
        for (int i = 1; i <= 5; i++) {
            String subRes = getBrandsByGroupAndRankAnswer(group, rankService.getRankById(i), 3);
            if (subRes.contains("没有"))
                continue;
            if (i != 1)
                subRes = subRes.substring(group.getName().length() + 5);
            res.append(subRes);
        }
        res = new StringBuilder(res.toString().replaceAll("。", "，"));
        res = new StringBuilder(res.substring(0, res.length() - 1) + "。");
        return res.toString();
    }

    @Override
    public String getRelationBetweenBrandAndGroup(Brand brand, Group group) {
        if (brand == null)
            return "没有关于这个酒店的信息。";
        if (group == null)
            return "没有关于这个集团的信息。";
        if (brand.getGid() == group.getId()) {
            return "是的。";
        } else {
            return "不是。" + getGroupByBrandAnswer(brand);
        }
    }

    @Override
    public String getGroupByBrandAnswer(Brand brand) {
        if (brand == null)
            return "没有找到这个酒店所属的集团。";
        Group group = groupService.getGroupById(brand.getGid());
        if (group == null)
            return "没有找到这个酒店所属的集团。";
        return brand.getName() + "隶属于" + group.getName() + "。";
    }

    @Override
    public String getRankByBrandAnswer(Brand brand) {
        if (brand == null)
            return "没有找到这个酒店的档次信息。";
        Rank rank = rankService.getRankById(brand.getRid());
        if (rank == null)
            return "没有找到这个酒店的档次信息。";
        return brand.getName() + "的定位是" + rank.getName() + "。";
    }

    @Override
    public String getBrandsByRankAnswer(Rank rank) {
        if (rank == null)
            return "没有这个档次信息。";
        List<Brand> brandList = new ArrayList<>();
        for (Brand brand : brandRepository.findTop8ByRidAndPriority(rank.getId(), "高")) {
            if (brandList.size() == 8)
                break;
            brandList.add(brand);
        }
        for (Brand brand : brandRepository.findTop8ByRidAndPriority(rank.getId(), "中")) {
            if (brandList.size() == 8)
                break;
            brandList.add(brand);
        }
        for (Brand brand : brandRepository.findTop8ByRidAndPriority(rank.getId(), "低")) {
            if (brandList.size() == 8)
                break;
            brandList.add(brand);
        }

        StringBuilder res = new StringBuilder("推荐的" + rank.getName() + "酒店有");
        for (Brand brand : brandList) {
            res.append(brand.getName()).append("、");
        }
        res.deleteCharAt(res.length() - 1);
        res.append("等。");
        return res.toString();
    }

    @Override
    public String getBrandsByGroupAndRankAnswer(Group group, Rank rank, int maxNum) {
        if (group == null)
            return "没有这个集团信息。";
        if (rank == null)
            return "没有这个档次信息。";
        List<Brand> brandList = new ArrayList<>();
        for (Brand brand : brandRepository.findTop8ByGidAndRidAndPriority(group.getId(), rank.getId(), "高")) {
            if (brandList.size() == maxNum)
                break;
            brandList.add(brand);
        }
        for (Brand brand : brandRepository.findTop8ByGidAndRidAndPriority(group.getId(), rank.getId(), "中")) {
            if (brandList.size() == maxNum)
                break;
            brandList.add(brand);
        }
        for (Brand brand : brandRepository.findTop8ByGidAndRidAndPriority(group.getId(), rank.getId(), "低")) {
            if (brandList.size() == maxNum)
                break;
            brandList.add(brand);
        }
        if (brandList.isEmpty())
            return group.getName() + "旗下没有该档次的酒店。";

        StringBuilder res = new StringBuilder(group.getName() + "旗下推荐的" + rank.getName() + "酒店有");
        for (Brand brand : brandList) {
            res.append(brand.getName()).append("、");
        }
        res.deleteCharAt(res.length() - 1);
        res.append("等。");
        return res.toString();
    }

    @Override
    public String getAppAndPlatByBrandAnswer(Brand brand) {
        if (brand == null)
            return "没有找到这个酒店的信息。";
        Group group = groupService.getGroupById(brand.getGid());
        String res = "";
        res += brand.getName();
        res += "可以在";
        res += group.getPlatform() + "和" + group.getHomepage() + "官网进行预订。";
        return res;
    }

    @Override
    public String getDiscountByBrandAndVipAnswer(Brand brand, Vip vip) {
        if (brand == null)
            return "没有找到这个酒店的信息。";
        if (vip == null)
            return "没有找到这个会员卡的信息。";

        Privilege privilege = privilegeService.getPrivilegeByVidAndBid(vip.getId(), brand.getId());
        if (privilege == null)
            return vip.getName() + "在预定" + brand.getName() + "时不享受权益。";

        String res = "";
        res += vip.getName();
        res += "在预定";
        res += brand.getName();
        res += "时享受";
        res += privilege.getDiscount();
        res += "优惠。";
        return res;
    }

    @Override
    public String getPrivilegeAnswerByBrandAndVipAnswer(Brand brand, Vip vip) {
        if (brand == null)
            return "没有找到这个酒店的信息。";
        if (vip == null)
            return "没有找到这个会员卡的信息。";

        Privilege privilege = privilegeService.getPrivilegeByVidAndBid(vip.getId(), brand.getId());
        if (privilege == null)
            return brand.getName() + "不在" + vip.getName() + "的权益范围内。";

        String res = getDiscountByBrandAndVipAnswer(brand, vip);
        res = res.replace('。', '，');
        res += "最晚" + privilege.getCheckout() + "退房，";
        if (privilege.getBreakfast() == 0) {
            res += "无免费早餐赠送。";
        } else {
            res += "赠送" + privilege.getBreakfast() + "份免费早餐。";
        }

        return res;
    }

    @Override
    public String getBreakfastDetailByBrandAnswer(Brand brand) {
        if (brand == null)
            return "没有找到这个酒店的信息。";

        List<Privilege> privilegeList = privilegeRepository.findAllByBidOrderByBreakfastAsc(brand.getId());
        if (privilegeList.isEmpty() || privilegeList.get(privilegeList.size() - 1).getBreakfast() == 0)
            return brand.getName() + "不提供免费早餐。";

        StringBuilder res = new StringBuilder(brand.getName());
        long lastBreakfastNum = 100;
        for (Privilege privilege : privilegeList) {
            long currBreakfastNum = privilege.getBreakfast();
            if (currBreakfastNum == 0)
                continue;

            if (currBreakfastNum == lastBreakfastNum)
                res.append(vipService.getVipById(privilege.getVid()).getName()).append("、");
            else {
                if (lastBreakfastNum != 100) {
                    res.deleteCharAt(res.length() - 1);
                    res.append("提供").append(lastBreakfastNum).append("份免费早餐，");
                }
                res.append("对").append(vipService.getVipById(privilege.getVid()).getName()).append("、");
                lastBreakfastNum = currBreakfastNum;
            }
        }

        res.deleteCharAt(res.length() - 1);
        res.append("提供").append(lastBreakfastNum).append("份免费早餐。");
        return res.toString();
    }

    @Override
    public String getCheckoutDetailByBrandAnswer(Brand brand) {
        if (brand == null)
            return "没有找到这个酒店的信息。";
        List<Privilege> privilegeList = privilegeRepository.findAllByBidOrderByCheckoutAsc(brand.getId());
        if (privilegeList.isEmpty())
            return "没有找到这个酒店的关于退房时间的信息。";

        StringBuilder res = new StringBuilder(brand.getName());
        String lastCheckout = "00:00";
        for (Privilege privilege : privilegeList) {
            String currCheckout = privilege.getCheckout();

            if (currCheckout.equals(lastCheckout))
                res.append(vipService.getVipById(privilege.getVid()).getName()).append("、");
            else {
                if (!lastCheckout.equals("00:00")) {
                    res.deleteCharAt(res.length() - 1);
                    res.append("可以延迟到").append(lastCheckout).append("退房，");
                }
                res.append("对").append(vipService.getVipById(privilege.getVid()).getName()).append("、");
                lastCheckout = currCheckout;
            }
        }

        res.deleteCharAt(res.length() - 1);
        res.append("可以延迟到").append(lastCheckout).append("退房。");
        return res.toString();
    }

    @Override
    public String getDetailByBrandName(String name) {
        Brand brand = brandService.getBrandByNameContains(name);
        if (brand == null)
            return "暂无相关信息。";
        List<Privilege> privilegeList = privilegeRepository.findAllByBidOrderByCheckoutAsc(brand.getId());
        if (privilegeList.isEmpty())
            return "暂无相关信息。";

        StringBuilder detail = new StringBuilder();

        boolean isBegin = true;
        for (Privilege privilege : privilegeList) {
            String res = getDiscountByBrandAndVipAnswer(brand, vipService.getVipById(privilege.getVid()));
            res = res.replace('。', '，');
            res += "最晚" + privilege.getCheckout() + "退房，";
            if (privilege.getBreakfast() == 0) {
                res += "无免费早餐赠送。";
            } else {
                res += "赠送" + privilege.getBreakfast() + "份免费早餐。";
            }
            if (!isBegin)
                detail.append("\n");
            detail.append(res);
            isBegin = false;
        }

        return detail.toString();
    }
}
