package com.example.backend.Service;

import com.example.backend.Model.Brand;
import com.example.backend.Model.Group;
import com.example.backend.Model.Rank;
import com.example.backend.Model.Vip;

public interface IQaService {

    /**
     * @param questionIndex
     * @param groupName
     * @param brandName
     * @param rankName
     * @return
     */
    String getAnswer(int questionIndex, String groupName, String brandName, String rankName, String vipName);

    /**
     * @param group
     * @return
     */
    String getGroupIntroAnswer(Group group);

    /**
     * @param group
     * @return
     */
    String getBrandsUnderGroupAnswer(Group group);

    /**
     * @param brand
     * @return
     */
    String getGroupByBrandAnswer(Brand brand);

    /**
     * @param brand
     * @param group
     * @return
     */
    String getRelationBetweenBrandAndGroup(Brand brand, Group group);

    /**
     * @param brand
     * @return
     */
    String getRankByBrandAnswer(Brand brand);

    /**
     * @param rank
     * @return
     */
    String getBrandsByRankAnswer(Rank rank);

    /**
     * @param group
     * @param rank
     * @return
     */
    String getBrandsByGroupAndRankAnswer(Group group, Rank rank, int maxNum);

    /**
     * @param brand
     * @return
     */
    String getAppAndPlatByBrandAnswer(Brand brand);

    /**
     * @param brand
     * @param vip
     * @return
     */
    String getDiscountByBrandAndVipAnswer(Brand brand, Vip vip);

    /**
     * @param brand
     * @param vip
     * @return
     */
    String getPrivilegeAnswerByBrandAndVipAnswer(Brand brand, Vip vip);

    /**
     * @param brand
     * @return
     */
    String getBreakfastDetailByBrandAnswer(Brand brand);

    /**
     * @param brand
     * @return
     */
    String getCheckoutDetailByBrandAnswer(Brand brand);

    /**
     * @param name
     * @return
     */
    String getDetailByBrandName(String name);
}
