package com.neuedu.mapper;

import com.neuedu.entity.CheckingItem;
import com.neuedu.entity.CheckingItemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CheckingItemMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_checking_item
     *
     * @mbg.generated Tue Apr 21 09:05:41 CST 2020
     */
    long countByExample(CheckingItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_checking_item
     *
     * @mbg.generated Tue Apr 21 09:05:41 CST 2020
     */
    int deleteByExample(CheckingItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_checking_item
     *
     * @mbg.generated Tue Apr 21 09:05:41 CST 2020
     */
    int deleteByPrimaryKey(Integer checkId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_checking_item
     *
     * @mbg.generated Tue Apr 21 09:05:41 CST 2020
     */
    int insert(CheckingItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_checking_item
     *
     * @mbg.generated Tue Apr 21 09:05:41 CST 2020
     */
    int insertSelective(CheckingItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_checking_item
     *
     * @mbg.generated Tue Apr 21 09:05:41 CST 2020
     */
    List<CheckingItem> selectByExample(CheckingItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_checking_item
     *
     * @mbg.generated Tue Apr 21 09:05:41 CST 2020
     */
    CheckingItem selectByPrimaryKey(Integer checkId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_checking_item
     *
     * @mbg.generated Tue Apr 21 09:05:41 CST 2020
     */
    int updateByExampleSelective(@Param("record") CheckingItem record, @Param("example") CheckingItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_checking_item
     *
     * @mbg.generated Tue Apr 21 09:05:41 CST 2020
     */
    int updateByExample(@Param("record") CheckingItem record, @Param("example") CheckingItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_checking_item
     *
     * @mbg.generated Tue Apr 21 09:05:41 CST 2020
     */
    int updateByPrimaryKeySelective(CheckingItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_checking_item
     *
     * @mbg.generated Tue Apr 21 09:05:41 CST 2020
     */
    int updateByPrimaryKey(CheckingItem record);
}