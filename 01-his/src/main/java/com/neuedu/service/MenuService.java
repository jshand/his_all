package com.neuedu.service;

import com.neuedu.entity.Menu;
import com.neuedu.entity.MenuExample;
import com.neuedu.framework.HisConstants;
import com.neuedu.mapper.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 项目    ： his_all
 * 创建时间 ：2020/4/30  9:02 30
 * author  ：jshand-root
 * site    :  http://314649444.iteye.com
 * 描述     :
 */
@Service
public class MenuService {

    @Autowired
    MenuMapper menuMapper;


    /**
     *  查询全部菜单
     * @param menu
     * @return
     */
    public List<Menu> queryAll(Menu menu) {

        MenuExample menuExample = new MenuExample();
        MenuExample.Criteria criteria = menuExample.createCriteria(); //()

        criteria.andIsDelEqualTo(HisConstants.RECORD_IS_DEL_FALSE);//

        return menuMapper.selectByExample(menuExample);
    }

    /**
     * 用于修改 菜单项
     * @param menu
     * @return
     */
    public boolean edit(Menu menu) {

        return menuMapper.updateByPrimaryKeySelective(menu) >0;

    }

    /**
     * 添加菜单项
     * @param menu
     * @return
     */
    public boolean save(Menu menu) {
        return menuMapper.insertSelective(menu) >0;

    }

    /**
     * 设置上级Id
     * @param menuId
     * @param parentId
     * @return
     */
    public boolean setParentId(Integer menuId, Integer parentId) {

        Menu menu = new Menu();
        menu.setMenuId(menuId);
        menu.setParentId(parentId);
        return menuMapper.updateByPrimaryKeySelective(menu) >0;
    }
}
