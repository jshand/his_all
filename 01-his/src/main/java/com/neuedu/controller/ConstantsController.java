package com.neuedu.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.neuedu.entity.Constants;
import com.neuedu.entity.ConstantsType;
import com.neuedu.framework.BaseController;
import com.neuedu.service.ConstantsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 项目    ： his_all
 * 创建时间 ：2020/4/28  11:27 28
 * author  ：jshand-root
 * site    :  http://314649444.iteye.com
 * 描述     : 常数   相关的控制器
 */
@RestController
@RequestMapping("constants")
public class ConstantsController extends BaseController {

    @Autowired
    ConstantsService constantsService;

    /**
     * 常数类别的分页查询
     * @param start
     * @param pageSize
     * @param draw
     * @param constantsType
     * @return
     */
    @RequestMapping("constantsTypePageList")
    public Map constantsTypePageList(
            @RequestParam(defaultValue = "0") int start,
            @RequestParam(value ="length",defaultValue = "10") int pageSize,
            int draw  , ConstantsType constantsType){


        Page<ConstantsType> page = PageHelper.offsetPage(start,pageSize);

        List<ConstantsType> data = constantsService.queryConstantsTypeByPage(constantsType);

        return super.pageReuslt(draw,page);
    }

    /**
     * 常数项 分页查询
     * @param start
     * @param pageSize
     * @param draw
     * @param constants
     * @return
     */
    @RequestMapping("constantsPageList")
    public Map constantsPageList(
            @RequestParam(defaultValue = "0") int start,
            @RequestParam(value ="length",defaultValue = "10") int pageSize,
            int draw  , Constants constants){


        Page<Constants> page = PageHelper.offsetPage(start,pageSize);

        List<Constants> data = constantsService.queryConstantsByPage(constants);

        return super.pageReuslt(draw,page);
    }


    /**
     * 新增常数列别
     * @param code
     * @param name
     * @return
     */
    @RequestMapping("saveType/{code}/{name}")
    public Map saveType(@PathVariable(name="code") String code ,@PathVariable(name="name") String name){
        boolean success = constantsService.saveType(code,name);
        return super.ajaxSucess(success);
    }


    @RequestMapping("saveConstants")
    public Map saveType(Constants constants){
        boolean success = constantsService.saveConstants(constants);
        return super.ajaxSucess(success);
    }

    @RequestMapping("editType/{typeId}/{code}/{name}")
    public Map editType(
            @PathVariable(name="typeId") Integer typeId ,
            @PathVariable(name="code") String code,
            @PathVariable(name="name") String name){
        boolean success = constantsService.editType(typeId,code,name);

        return super.ajaxSucess(success);
    }


    /**
     * 常量类别删除
     * @param typeId
     * @return
     */
    @RequestMapping("constantsTypeDel/{typeId}")
    public Map constantsTypeDel(@PathVariable(name="typeId") Integer typeId){


        boolean success = false;
        String msg = "";
        try {
            success = constantsService.constantsTypeDel(typeId);
        } catch (Exception e) {
           msg = e.getMessage();
        }

        return super.ajaxSucess(success,msg);
    }


    /**
     * 根据主键查询常数类别
     * http://127.0.0.1:80/constants/constantsTypeQuery/2
     * @param typeId
     * @return
     */
    @RequestMapping("constantsTypeQuery/{typeId}")
    public ConstantsType constantsTypeQuery(@PathVariable(name="typeId") Integer typeId){

        ConstantsType constantsType = constantsService.constantsTypeQuery(typeId);

        return constantsType;
    }





}
