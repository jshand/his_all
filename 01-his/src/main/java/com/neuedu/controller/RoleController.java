package com.neuedu.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.neuedu.entity.Role;
import com.neuedu.entity.RoleMenuKey;
import com.neuedu.framework.BaseController;
import com.neuedu.mapper.RoleMapper;
import com.neuedu.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 项目    ： his_all
 * 创建时间 ：2020/4/20  14:29 20
 * author  ：jshand-root
 * site    :  http://314649444.iteye.com
 * 描述     : 角色相关操作
 */
@RestController
@RequestMapping("role")
public class RoleController extends BaseController {


    @Autowired
    RoleMapper roleMapper;

    /**
     *  "draw": 1,
     *   "recordsTotal": 5,
     *   "recordsFiltered": 5,
     *   "data":[
         *    {
         *       "id": "a1",
         *       "roleName": "abc0",
         *       "age": 18
         *     },
     *   ]
     *
     *   http://127.0.0.1:8080/role/pageList
     * @return
     */
    @RequestMapping("pageList")
    public Map pageList(
                        @RequestParam(defaultValue = "0") int start,
                        @RequestParam(value ="length",defaultValue = "10") int pageSize,
                        int draw     ){

        Map pageReuslt = new HashMap();

//        Page<Role> page = PageHelper.startPage(pageNum, pageSize);

        Page<Role> page = PageHelper.offsetPage(start,pageSize);

        List<Role> data = roleMapper.selectByExample(null);

        pageReuslt.put("draw",draw);
        pageReuslt.put("recordsTotal", page.getTotal());
        pageReuslt.put("recordsFiltered", page.getTotal());
        pageReuslt.put("data",data);

        return pageReuslt;
    }


    @Autowired
    RoleService roleService;


    @RequestMapping("grant")
    public Map  grant(Integer roleId, Integer[] menuIds){

        boolean success = roleService.grant(roleId,menuIds);

        return super.ajaxSucess(success);
    }


    /**
     *
     * @param roleId
     * @return
     *
     * [
     *      {role:'5',menuId:'1'},
     *      {role:'5',menuId:'2'},
     *      {role:'5',menuId:'3'},
     *      {role:'5',menuId:'4'},
     *
     * ]
     */
    @RequestMapping("queryRoleMenu/{roleId}")
    public List<RoleMenuKey>   queryRoleMenu(@PathVariable(name="roleId") Integer roleId){

        List<RoleMenuKey> list = roleService.queryRoleMenu(roleId);

        return list;
    }

}
