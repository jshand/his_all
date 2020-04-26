package com.neuedu.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.neuedu.entity.CheckingItem;
import com.neuedu.entity.InspectItem;
import com.neuedu.framework.BaseController;
import com.neuedu.service.JcService;
import com.neuedu.service.JyService;
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
 * 描述     : 检验
 */
@RestController
@RequestMapping("jy")
public class JyController extends BaseController {

    @Autowired
    JyService jyService;


    @RequestMapping("pageList")
    public Map pageList(
            @RequestParam(defaultValue = "0") int start,
            @RequestParam(value ="length",defaultValue = "10") int pageSize,
            int draw , InspectItem inspectItem){

        Map pageReuslt = new HashMap();

        Page<CheckingItem> page = PageHelper.offsetPage(start,pageSize);

        List<InspectItem> data = jyService.pageList(inspectItem);


        return super.pageReuslt(draw,page);
    }


}
