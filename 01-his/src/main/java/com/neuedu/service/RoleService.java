package com.neuedu.service;

import com.neuedu.entity.RoleMenuExample;
import com.neuedu.entity.RoleMenuKey;
import com.neuedu.mapper.RoleMenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 项目    ： his_all
 * 创建时间 ：2020/4/30  14:06 30
 * author  ：jshand-root
 * site    :  http://314649444.iteye.com
 * 描述     :
 */
@Service
public class RoleService {

    @Autowired
    RoleMenuMapper roleMenuMapper;

    /**
     * 1 1
     * 1 2
     *
     * 1 1
     * 1 2
     * 1 3
     *
     * @param roleId
     * @param menuIds
     * @return
     */

    public boolean grant(Integer roleId, Integer[] menuIds) {

        //先删除已授权的菜单，
        RoleMenuExample roleMenuExample = new RoleMenuExample();
        roleMenuExample.createCriteria().andRoleIdEqualTo(roleId); // delete from t_role_menu where role_id = ?
        roleMenuMapper.deleteByExample(roleMenuExample);


        //插入角色权限
        int count = 0;
        for (Integer menuId : menuIds) {
            RoleMenuKey roleMenu = new RoleMenuKey();
            roleMenu.setRoleId(roleId);
            roleMenu.setMenuId(menuId);
            count += roleMenuMapper.insertSelective(roleMenu);
        }

        return count == menuIds.length;

    }

    /**
     * 根据角色查询 已授权的菜单项目
     * @param roleId
     * @return
     */
    public List<RoleMenuKey> queryRoleMenu(Integer roleId) {

        RoleMenuExample roleMenuExample = new RoleMenuExample();
        roleMenuExample.createCriteria().andRoleIdEqualTo(roleId); //select * from t_role_menu where role_Id = 5

        List<RoleMenuKey> list  = roleMenuMapper.selectByExample(roleMenuExample);

        return list;
    }
}
