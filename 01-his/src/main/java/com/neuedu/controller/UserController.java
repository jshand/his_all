package com.neuedu.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.neuedu.entity.Dept;
import com.neuedu.entity.User;
import com.neuedu.framework.BaseController;
import com.neuedu.framework.HisConstants;
import com.neuedu.framework.cache.HisCache;
import com.neuedu.service.DeptService;
import com.neuedu.service.UserSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * 项目    ： his_all
 * 创建时间 ：2020/4/29  9:31 29
 * author  ：jshand-root
 * site    :  http://314649444.iteye.com
 * 描述     : 部门的控制器
 */
@RestController
@RequestMapping("user")
public class UserController extends BaseController {


    @Autowired
    UserSerivce userSerivce;


//    @Autowired
//    HisCache cache;


    @RequestMapping("pageList")
    public Map pageList(
            @RequestParam(defaultValue = "0") int start,
            @RequestParam(value = "length", defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "1") int draw, User user) {


        Page<User> page = PageHelper.offsetPage(start, pageSize);

        List<User> data = userSerivce.pageList(user);

        return super.pageReuslt(draw, page);
    }


    /**
     * 新增部门信息
     *
     * @param user
     * @return
     */
    @RequestMapping("save")
    public Map save(User user) {

        boolean success = userSerivce.save(user);
        return super.ajaxSucess(success);
    }


    /**
     * 根据主键查询部门 信息
     *
     * @param userId
     * @return
     */
    @RequestMapping("queryById/{userId}")
    public User queryById(@PathVariable(name = "userId") Integer userId) {

        User user = userSerivce.queryById(userId);

        return user;
    }


    /**
     * 修改用户信息
     *
     * @param user
     * @return
     */
    @RequestMapping("edit")
    public Map edit(User user) {

        boolean success = userSerivce.edit(user);
        return super.ajaxSucess(success);
    }


    /*
     * 新增部门信息
     * @param deptCode
     * @return
       */
    @RequestMapping("del/{userId}")
    public Map del(@PathVariable(name="userId") Integer userId){

        boolean success = userSerivce.del(userId);
        return super.ajaxSucess(success);
    }




    @RequestMapping("grant")
    public Map  grant(Integer userId, Integer[] roleIds){

        boolean success = userSerivce.grant(userId,roleIds);

        return super.ajaxSucess(success);
    }


    /**
     * 根据登录的用户查询 菜单权限
     */
    @RequestMapping("queryMenu")
    public String queryMenu(HttpSession session){
        User user = (User) session.getAttribute(HisConstants.LOGIN_USER);
        Integer userId = user.getUserId();

        String menuHtml = userSerivce.queryMenu(userId);

        return menuHtml;
    }


}
