package com.neuedu.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.neuedu.entity.CheckingItem;
import com.neuedu.entity.Role;
import com.neuedu.service.JcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 项目    ： his_all
 * 创建时间 ：2020/4/24  15:05 24
 * author  ：jshand-root
 * site    :  http://314649444.iteye.com
 * 描述     : 检查
 */
@RestController
@RequestMapping("jc")
public class JcController {

    @Autowired
    JcService jcService;


    @RequestMapping("pageList")
    public Map pageList(
            @RequestParam(defaultValue = "0") int start,
            @RequestParam(value ="length",defaultValue = "10") int pageSize,
            int draw , CheckingItem checkingItem){

        Map pageReuslt = new HashMap();

        Page<CheckingItem> page = PageHelper.offsetPage(start,pageSize);

        List<CheckingItem> data = jcService.pageList(checkingItem);

        pageReuslt.put("draw",draw);
        pageReuslt.put("recordsTotal", page.getTotal());
        pageReuslt.put("recordsFiltered", page.getTotal());
        pageReuslt.put("data",data);

        return pageReuslt;
    }


}
