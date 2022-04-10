package com.fj.crm.workbench.mapper;

import com.fj.crm.workbench.domain.MarketingActivities;

import java.util.List;
import java.util.Map;

public interface MarketingActivitiesMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_activity
     *
     * @mbggenerated Sun Apr 03 22:53:06 CST 2022
     */
    int deleteByPrimaryKey(String id);


    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_activity
     *
     * @mbggenerated Sun Apr 03 22:53:06 CST 2022
     */
    int insertSelective(MarketingActivities record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_activity
     *
     * @mbggenerated Sun Apr 03 22:53:06 CST 2022
     */
    MarketingActivities selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_activity
     *
     * @mbggenerated Sun Apr 03 22:53:06 CST 2022
     */
    int updateByPrimaryKeySelective(MarketingActivities record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_activity
     *
     * @mbggenerated Sun Apr 03 22:53:06 CST 2022
     */
    int updateByPrimaryKey(MarketingActivities record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_activity
     *
     * @mbggenerated Sun Apr 03 22:53:06 CST 2022
     */
    int insertActivity(MarketingActivities record);

    /*
    * 根据条件查询市场活动
    * */
    List<MarketingActivities> selectActivitiesByConditionForPage(Map<String,Object> map);

    /*
    * 根据条件查询市场活动，返回总条数
    * */
    Integer selectTotalActivitiesByCondition(Map<String,Object> map);

    /*
    * 根据id删除指定市场活动
    * */
    Integer deleteActivitiesByIDs(List<String> ids);

    /*
    * 根据ID查询市场活动
    * */
    MarketingActivities queryActivityById(String id);

    /*
    * 更新指定市场活动
    * */
    Integer updateActivityById(MarketingActivities activity);

    /*
    * 查询所有市场活动
    * */
    List<MarketingActivities> queryAllActivities();

    /*
    * 批量创建市场活动
    * */
    Integer insertBatchActivities(List<MarketingActivities> activities);

}