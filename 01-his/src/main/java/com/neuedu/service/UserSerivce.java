package com.neuedu.service;

import com.neuedu.entity.*;
import com.neuedu.framework.HisConstants;
import com.neuedu.mapper.PowerMapper;
import com.neuedu.mapper.UserMapper;
import com.neuedu.mapper.UserRoleMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 项目    ： his_all
 * 创建时间 ：2020/4/29  11:33 29
 * author  ：jshand-root
 * site    :  http://314649444.iteye.com
 * 描述     :
 */
@Service
public class UserSerivce {
    @Autowired
    UserMapper userMapper;

    /**
     * 分页查询用户信息 根据昵称查询
     * @param user
     * @return
     */
    public List<User> pageList(User user) {
        UserExample example = new UserExample();
        UserExample.Criteria cri = example.createCriteria().andIsDelEqualTo(HisConstants.RECORD_IS_DEL_FALSE);

        if(StringUtils.isNotBlank(user.getDisplayName())){
            cri.andDisplayNameLike("%"+user.getDisplayName()+"%");
        }

        return userMapper.selectByExample(example);
    }

    /**
     * 新增用户
     * @param user
     * @return
     */
    public boolean save(User user) {
        return userMapper.insertSelective(user) > 0 ;
    }

    /**
     * 根据主键查询用户
     * @param userId
     * @return
     */
    public User queryById(Integer userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    /**
     * 根据主键更新操作
     * @param user
     * @return
     */
    public boolean edit(User user) {
        return userMapper.updateByPrimaryKeySelective(user) >0;
    }

    /**
     * 逻辑删除用户表数据
     *
     * @param userId
     * @return
     */
    public boolean del(Integer userId) {
        User user = new User();

        user.setUserId(userId);
        user.setIsDel(HisConstants.RECORD_IS_DEL_TRUE); //删除的状态

        return userMapper.updateByPrimaryKeySelective(user) > 0 ;
    }


    @Autowired
    UserRoleMapper userRoleMapper;

    public boolean grant(Integer userId, Integer[] roleIds) {

        //先删除已授权的角色，
        UserRoleExample userRoleExample = new UserRoleExample();
        userRoleExample.createCriteria().andUserIdEqualTo(userId); // delete from t_user_role where user_id = ?
        userRoleMapper.deleteByExample(userRoleExample);


        //插入用户权限
        int count = 0;
        for (Integer roleId : roleIds) {
            UserRoleKey userRole = new UserRoleKey();
            userRole.setUserId(userId);
            userRole.setRoleId(roleId);
            count += userRoleMapper.insertSelective(userRole);
        }


        return count == roleIds.length;

    }

    @Autowired
    PowerMapper powerMapper;
    /**
     *
     * @param userId
     * @return
     */

    public String queryMenu(Integer userId) {
        StringBuffer html = new StringBuffer();
       List<Menu> list =  powerMapper.queryMenu(userId);

        for (Menu menu : list) {
            if(menu.getParentId()!=null && menu.getParentId() == 0){
                html.append("<dl id=\"menu-constants\">\n" +
                        "\t\t\t<dt><i class=\"Hui-iconfont\">&#xe620;</i>"+menu.getMenuName()+"<i class=\"Hui-iconfont menu_dropdown-arrow\">&#xe6d5;</i></dt>\n" +
                        "\t\t\t<dd>\n" +
                        "\t\t\t\t<ul>");

                appendChild(menu,list,html);


                html.append("</ul>\n" +
                        "\t\t</dd> </dl>");
            }
        }

        return html.toString();
    }

    private void appendChild(Menu menu ,List<Menu> list, StringBuffer html) {
        for (Menu menu1 : list) {
            if(menu.getMenuId() == menu1.getParentId()){
                html.append("\t\t\t\t\t<li><a data-href=\""+menu1.getUrl()+"\" data-title=\""+menu1.getMenuName()+"\" href=\"javascript:void(0)\">"+menu1.getMenuName()+"</a></li>\n");
            }
        }
    }
}
