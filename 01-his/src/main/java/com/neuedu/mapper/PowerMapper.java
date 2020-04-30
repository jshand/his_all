package com.neuedu.mapper;

import com.neuedu.entity.Menu;

import java.util.List;

/**
 * 项目    ： his_all
 * 创建时间 ：2020/4/30  14:56 30
 * author  ：jshand-root
 * site    :  http://314649444.iteye.com
 * 描述     : 权限
 */
public interface PowerMapper {

    List<Menu> queryMenu(Integer userId);

}
