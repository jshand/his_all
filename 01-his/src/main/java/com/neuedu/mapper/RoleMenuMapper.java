package com.neuedu.mapper;

import com.neuedu.entity.RoleMenuExample;
import com.neuedu.entity.RoleMenuKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RoleMenuMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_role_menu
     *
     * @mbg.generated Tue Apr 21 09:05:41 CST 2020
     */
    long countByExample(RoleMenuExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_role_menu
     *
     * @mbg.generated Tue Apr 21 09:05:41 CST 2020
     */
    int deleteByExample(RoleMenuExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_role_menu
     *
     * @mbg.generated Tue Apr 21 09:05:41 CST 2020
     */
    int deleteByPrimaryKey(RoleMenuKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_role_menu
     *
     * @mbg.generated Tue Apr 21 09:05:41 CST 2020
     */
    int insert(RoleMenuKey record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_role_menu
     *
     * @mbg.generated Tue Apr 21 09:05:41 CST 2020
     */
    int insertSelective(RoleMenuKey record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_role_menu
     *
     * @mbg.generated Tue Apr 21 09:05:41 CST 2020
     */
    List<RoleMenuKey> selectByExample(RoleMenuExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_role_menu
     *
     * @mbg.generated Tue Apr 21 09:05:41 CST 2020
     */
    int updateByExampleSelective(@Param("record") RoleMenuKey record, @Param("example") RoleMenuExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_role_menu
     *
     * @mbg.generated Tue Apr 21 09:05:41 CST 2020
     */
    int updateByExample(@Param("record") RoleMenuKey record, @Param("example") RoleMenuExample example);
}