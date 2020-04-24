package com.neuedu.controller;

import com.neuedu.entity.MedicalRecord;
import com.neuedu.entity.MedicalRecordWithBLOBs;
import com.neuedu.framework.BaseController;
import com.neuedu.service.MzblService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public List<MedicalRecord> queryMedicalRecord(){

        return mzblService.queryMedicalRecord();
    }




    @RequestMapping("save")
    public Map save(MedicalRecordWithBLOBs mr){

        //保存 看诊信息
        boolean success = mzblService.save(mr);


        return super.ajaxSucess(success);
    }





}
