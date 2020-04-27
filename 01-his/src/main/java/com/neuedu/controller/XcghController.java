package com.neuedu.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.neuedu.entity.FeeRecord;
import com.neuedu.entity.MedicalRecord;
import com.neuedu.entity.Role;
import com.neuedu.framework.BaseController;
import com.neuedu.service.XcghService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 项目    ： his_all
 * 创建时间 ：2020/4/22  9:20 22
 * author  ：jshand-root
 * site    :  http://314649444.iteye.com
 * 描述     : 现场挂号
 */
@RestController
@RequestMapping("xcgh")
public class XcghController extends BaseController {

    @Autowired
    XcghService xcghService;


    /**
     * 现场挂号初始化页面 中的下拉选
     *  xcgh/initPage
     * @return
     */
    @RequestMapping("initPage")
    public Map initPage(){
        return xcghService.initPage();
    }


    /**
     * 挂号保存
     * @param mr
     * @return
     */
    @RequestMapping("ghbc")
    public Map ghbc(MedicalRecord  mr){

        boolean success = xcghService.ghbc(mr);

        return super.ajaxSucess(success);
    }




    @RequestMapping("pageList")
    public Map pageList(
            @RequestParam(defaultValue = "0") int start,
            @RequestParam(value ="length",defaultValue = "10") int pageSize,
            int draw     ){

        Map pageReuslt = new HashMap();

//        Page<Role> page = PageHelper.startPage(pageNum, pageSize);

        Page<MedicalRecord> page = PageHelper.offsetPage(start,pageSize);

        List<MedicalRecord> data = xcghService.listMedeiacalRecord();

        pageReuslt.put("draw",draw);
        pageReuslt.put("recordsTotal", page.getTotal());
        pageReuslt.put("recordsFiltered", page.getTotal());
        pageReuslt.put("data",data);

        return pageReuslt;
    }


    /**
     * 退号时查询 患者列表
     * @param start
     * @param pageSize
     * @param draw
     * @param mr
     * @return
     */
    @RequestMapping("tuiHaoPageList")
    public Map tuiHaoPageList(
            @RequestParam(defaultValue = "0") int start,
            @RequestParam(value ="length",defaultValue = "10") int pageSize,
            int draw    ,
            MedicalRecord mr){

        Map pageReuslt = new HashMap();

        Page<MedicalRecord> page = PageHelper.offsetPage(start,pageSize);

        List<MedicalRecord> data = xcghService.tuiHaolistMedeiacalRecord(mr);

        pageReuslt.put("draw",draw);
        pageReuslt.put("recordsTotal", page.getTotal());
        pageReuslt.put("recordsFiltered", page.getTotal());
        pageReuslt.put("data",data);

        return pageReuslt;
    }

    //http://127.0.0.1/xcgh/tuiHao/id
    @RequestMapping("tuiHao/{id}")
    public Map tuiHao(@PathVariable(name="id") Integer medicalId){

        boolean success = xcghService.tuiHao(medicalId);

        return super.ajaxSucess(success);
    }


    /**
     * 分页查询 收费项目
     *
     * xcgh/feePageList
     * @param start
     * @param pageSize
     * @param draw
     * @param mr
     * @return
     */
    @RequestMapping("feePageList")
    public Map feePageList(
            @RequestParam(defaultValue = "0") int start,
            @RequestParam(value ="length",defaultValue = "10") int pageSize,
            int draw ,MedicalRecord mr    ){

        Page<FeeRecord> page = PageHelper.offsetPage(start,pageSize);

        xcghService.feePageList(mr);


        return super.pageReuslt(draw,page);
    }

}
