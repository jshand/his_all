package com.neuedu.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.neuedu.entity.DoctorLevel;
import com.neuedu.entity.User;
import com.neuedu.framework.BaseController;
import com.neuedu.service.LevelService;
import com.neuedu.service.UserSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.print.Doc;
import java.util.List;
import java.util.Map;

/**
 * 项目    ： his_all
 * 创建时间 ：2020/4/29  9:31 29
 * author  ：jshand-root
 * site    :  http://314649444.iteye.com
 * 描述     : 问诊大夫的级别的控制器
 */
@RestController
@RequestMapping("level")
public class LevelController extends BaseController {


    @Autowired
    LevelService levelService;


    @Autowired
    UserSerivce userSerivce;

    @RequestMapping("pageList")
    public Map pageList(
            @RequestParam(defaultValue = "0") int start,
            @RequestParam(value = "length", defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "1") int draw, String levelName) {


        Page<DoctorLevel> page = PageHelper.offsetPage(start, pageSize);

        List<DoctorLevel> data = levelService.pageList(levelName);

        return super.pageReuslt(draw, page);
    }


    /**
     * 新增部门信息
     *
     * @param doctorLevel
     * @return
     */
    @RequestMapping("save")
    public Map save(DoctorLevel doctorLevel) {

        boolean success = levelService.save(doctorLevel);
        return super.ajaxSucess(success);
    }


    /**
     * 根据主键查询部门 信息
     *
     * @param levelId
     * @return
     */
    @RequestMapping("queryById/{levelId}")
    public DoctorLevel queryById(@PathVariable(name = "levelId") Integer levelId) {

        DoctorLevel doctorLevel = levelService.queryById(levelId);

        return doctorLevel;
    }


    /**
     * 修改用户信息
     *
     * @param doctorLevel
     * @return
     */
    @RequestMapping("edit")
    public Map edit(DoctorLevel doctorLevel) {

        boolean success = levelService.edit(doctorLevel);
        return super.ajaxSucess(success);
    }


    /*
     * 新增部门信息
     * @param deptCode
     * @return
       */
    @RequestMapping("del/{levelId}")
    public Map del(@PathVariable(name="levelId") Integer levelId){

        boolean success = levelService.del(levelId);
        return super.ajaxSucess(success);
    }
}
