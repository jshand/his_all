package com.neuedu.controller;

import com.neuedu.entity.Menu;
import com.neuedu.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 项目    ： his_all
 * 创建时间 ：2020/4/30  8:58 30
 * author  ：jshand-root
 * site    :  http://314649444.iteye.com
 * 描述     : 菜单
 */
@RestController
@RequestMapping("menu")
public class MenuController {


    @Autowired
    MenuService menuService;


    /**
     * 查询所有menu，用于生成Tree
     * @return
     */
    @RequestMapping("simpleMenuData")
    public List<Menu> simpleMenuData(){

        List<Menu> menuList =  menuService.queryAll(null);

        return menuList;
    }





}
