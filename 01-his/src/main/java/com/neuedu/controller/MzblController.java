package com.neuedu.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.neuedu.entity.CheckingItem;
import com.neuedu.entity.MedicalRecord;
import com.neuedu.entity.MedicalRecordWithBLOBs;
import com.neuedu.framework.BaseController;
import com.neuedu.service.MzblService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 项目    ： his_all
 * 创建时间 ：2020/4/23  16:54 23
 * author  ：jshand-root
 * site    :  http://314649444.iteye.com
 * 描述     : 门诊病历
 */
@RestController
@RequestMapping("mzbl")
public class MzblController extends BaseController {

    @Autowired
    MzblService mzblService;
    /**
     * 查询病历信息
     */
    @RequestMapping("queryMedicalRecord")
    public List<MedicalRecordWithBLOBs> queryMedicalRecord(){

        return mzblService.queryMedicalRecord();
    }




       @RequestMapping("save")
    public Map save(MedicalRecordWithBLOBs mr){

        //保存 看诊信息
        boolean success = mzblService.save(mr);


        return super.ajaxSucess(success);
    }


    /**
     * 申请检查
     * http://127.0.0.1/mzbl/sqjc/病历号/申请的检查项目id
     * @param medicalId
     * @param checkId
     * @return
     */
    @RequestMapping("sqjc/{medicalId}/{checkId}")
    public Map sqjc(@PathVariable(name="medicalId") Integer medicalId,@PathVariable(name="checkId") Integer checkId){

        boolean success = mzblService.sqjc(medicalId,checkId);

        return super.ajaxSucess(success);
    }


    /**
     * 根据病历号查询已申请的 检查
     * http://127.0.0.1:80/mzbl/queryApplyCheckingWithMedicalId/2
     * @param start
     * @param pageSize
     * @param draw
     * @param medicalId
     * @return
     */
    @RequestMapping("queryApplyCheckingWithMedicalId/{medicalId}")
    public Map pageList(
            @RequestParam(defaultValue = "0") int start,
            @RequestParam(value ="length",defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "1") int draw , @PathVariable(name="medicalId") int medicalId) {

        Page<CheckingItem> page = PageHelper.offsetPage(start,pageSize);
        mzblService.queryApplyCheckingWithMedicalId(medicalId);

        return pageReuslt(draw,page);
    }







}
