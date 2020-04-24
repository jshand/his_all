package com.neuedu.controller;

import com.neuedu.entity.MedicalRecord;
import com.neuedu.entity.MedicalRecordWithBLOBs;
import com.neuedu.service.MedicalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 项目    ： his_all
 * 创建时间 ：2020/4/24  14:00 24
 * author  ：jshand-root
 * site    :  http://314649444.iteye.com
 * 描述     : 病历的控制器
 */
@RestController
@RequestMapping("mr")
public class MedicalRecordController {


    @Autowired
    MedicalRecordService medicalRecordService;


    /**
     * 根据主键查询病历详情
     * @param medicalId 病历号
     * @return MedicalRecordWithBLOBs 病历详情
     * http://127.0.0.1/mr/queryMedicalRecordById/2
     */
    @RequestMapping("queryMedicalRecordById/{id}")
    public MedicalRecordWithBLOBs queryMedicalRecordById(@PathVariable(name ="id") Integer medicalId){

        //保存 看诊信息
        MedicalRecordWithBLOBs mr = medicalRecordService.queryMedicalRecordById(medicalId);

        return mr;
    }
}
