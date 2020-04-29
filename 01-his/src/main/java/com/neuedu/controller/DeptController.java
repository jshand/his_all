package com.neuedu.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.neuedu.entity.Dept;
import com.neuedu.entity.Role;
import com.neuedu.framework.BaseController;
import com.neuedu.framework.cache.HisCache;
import com.neuedu.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
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
@RequestMapping("dept")
public class DeptController extends BaseController {


    @Autowired
    DeptService deptService;


//    @Autowired
//    HisCache cache;


    @RequestMapping("pageList")
    public Map pageList(
            @RequestParam(defaultValue = "0") int start,
            @RequestParam(value ="length",defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "1") int draw   , Dept dept){


        Page<Dept> page = PageHelper.offsetPage(start,pageSize);

        List<Dept> data = deptService.pageList(dept);

        return super.pageReuslt(draw,page);
    }


    /**
     * 新增部门信息
     * @param dept
     * @return
     */
    @RequestMapping("save")
    public Map save(Dept dept){

        boolean success = deptService.save(dept);
        return super.ajaxSucess(success);
    }


    /**
     * 获取部门类型的列表
     * @return
     */
    @RequestMapping("getDeptTypeList")
    public List getDeptTypeList(){


        return HisCache.getConstantsListByConsType("BMLX");
    }






}
