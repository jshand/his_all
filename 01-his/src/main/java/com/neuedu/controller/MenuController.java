package com.neuedu.controller;

import com.neuedu.entity.Menu;
import com.neuedu.framework.BaseController;
import com.neuedu.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 项目    ： his_all
 * 创建时间 ：2020/4/30  8:58 30
 * author  ：jshand-root
 * site    :  http://314649444.iteye.com
 * 描述     : 菜单
 */
@RestController
@RequestMapping("menu")
public class MenuController extends BaseController {


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

    /**
     * 查询所有menu，用于生成Tree
     * @return
     */
    @RequestMapping("edit")
    public Map edit(Menu menu){

        boolean success = menuService.edit(menu);
        return super.ajaxSucess(success);
    }



    /**
     * 查询所有menu，用于生成Tree
     * @return
     */
    @RequestMapping("save")
    public Map save(Menu menu){

        boolean success = menuService.save(menu);
        return super.ajaxSucess(success);
    }


    @RequestMapping("setParentId/{menuId}/{parentId}")
    public Map setParentId(@PathVariable(name="menuId")Integer menuId , @PathVariable(name="parentId")Integer parentId ){
        boolean success = menuService.setParentId(menuId,parentId);
        return super.ajaxSucess(success);
    }

}
