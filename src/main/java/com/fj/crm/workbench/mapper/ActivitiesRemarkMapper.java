package com.fj.crm.workbench.mapper;

import com.fj.crm.workbench.domain.ActivitiesRemark;

import java.util.List;

public interface ActivitiesRemarkMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_activity_remark
     *
     * @mbggenerated Sun Apr 10 15:37:23 CST 2022
     */


    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_activity_remark
     *
     * @mbggenerated Sun Apr 10 15:37:23 CST 2022
     */
    int insertSelective(ActivitiesRemark record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_activity_remark
     *
     * @mbggenerated Sun Apr 10 15:37:23 CST 2022
     */

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_activity_remark
     *
     * @mbggenerated Sun Apr 10 15:37:23 CST 2022
     */


    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_activity_remark
     *
     * @mbggenerated Sun Apr 10 15:37:23 CST 2022
     */


    /*
    * 根据市场活动Id查询备注
    * */
    List<ActivitiesRemark> selectRemarkByActivityId(String id);

    /*
    * 插入一条市场活动备注
    * */
    int insert(ActivitiesRemark record);

    /*
    * 删除一条市场活动备注
    * */
    int deleteByPrimaryKey(String id);

    /*
    * 根据Id查询备注
    * */
    ActivitiesRemark selectByPrimaryKey(String id);

    /*
    * 根据Id修改备注
    * */
    int updateByPrimaryKeySelective(ActivitiesRemark record);
}