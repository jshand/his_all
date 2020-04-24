package com.neuedu.service;

import com.neuedu.entity.MedicalRecordWithBLOBs;
import com.neuedu.mapper.MedicalRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 项目    ： his_all
 * 创建时间 ：2020/4/24  14:01 24
 * author  ：jshand-root
 * site    :  http://314649444.iteye.com
 * 描述     :
 */
@Service
public class MedicalRecordService {

    @Autowired
    MedicalRecordMapper medicalRecordMapper;


    public MedicalRecordWithBLOBs queryMedicalRecordById(Integer medicalId) {

        return medicalRecordMapper.selectByPrimaryKey(medicalId);
    }
}
