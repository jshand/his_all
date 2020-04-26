package com.neuedu.service;

import com.neuedu.entity.*;
import com.neuedu.framework.HisConstants;
import com.neuedu.mapper.ApplyCheckingMapper;
import com.neuedu.mapper.CheckingItemMapper;
import com.neuedu.mapper.MedicalRecordMapper;
import com.neuedu.mapper.MzblMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目    ： his_all
 * 创建时间 ：2020/4/23  16:56 23
 * author  ：jshand-root
 * site    :  http://314649444.iteye.com
 * 描述     :
 */
@Service
public class MzblService {
    @Autowired
    MedicalRecordMapper medicalRecordMapper;

    @Autowired
    ApplyCheckingMapper applyCheckingMapper;

    @Autowired
    CheckingItemMapper checkingItemMapper;

    @Autowired
    MzblMapper mzblMapper;

    /**
     * 查询病历信息 查询当前看诊时间的
     * @return
     */
    public List<MedicalRecordWithBLOBs> queryMedicalRecord() {

        MedicalRecordExample ex1 = new MedicalRecordExample();
        ex1.createCriteria().andStatusEqualTo(HisConstants.MEDICAL_RECORD_STATUS_DZ);
        List<MedicalRecordWithBLOBs> dzList = medicalRecordMapper.selectByExampleWithBLOBs(ex1);



        MedicalRecordExample ex2 = new MedicalRecordExample();
        ex2.createCriteria().andStatusNotEqualTo(HisConstants.MEDICAL_RECORD_STATUS_DZ);
        List<MedicalRecordWithBLOBs> ykzList = medicalRecordMapper.selectByExampleWithBLOBs(ex2);


        MedicalRecord root = new MedicalRecord();
        root.setName("患者列表");
        root.setMedicalId(-99);

        MedicalRecord dz = new MedicalRecord();
        dz.setName("待诊");
        dz.setMedicalId(-1);
        dz.setDocId(root.getMedicalId());//借用医生id 作为上级id
        for (MedicalRecord medicalRecord : dzList) {
            medicalRecord.setDocId(dz.getMedicalId());
        }


        MedicalRecord yz = new MedicalRecord();
        yz.setName("已接诊");
        yz.setMedicalId(-2);
        yz.setDocId(root.getMedicalId());//借用医生id 作为上级id
        for (MedicalRecord medicalRecord : ykzList) {
            medicalRecord.setDocId(yz.getMedicalId());
        }


        List treeNode = new ArrayList();
        treeNode.add(root);
        treeNode.add(dz);
        treeNode.add(yz);
        treeNode.addAll(dzList);
        treeNode.addAll(ykzList);

        return treeNode;
    }

    /**
     * 保存门诊病历信息
     * @param mr
     * @return
     */
    public boolean save(MedicalRecordWithBLOBs mr) {

       int count =  medicalRecordMapper.updateByPrimaryKeySelective(mr);
       return count>0;
    }


    /**
     * 申请检查
     * @param medicalId
     * @param checkId
     * @return
     */
    public boolean sqjc(Integer medicalId, Integer checkId) {

        CheckingItem checkingItem = checkingItemMapper.selectByPrimaryKey(checkId);


        ApplyChecking applyChecking = new ApplyChecking();
        applyChecking.setMedicalId(medicalId);

        applyChecking.setCheckId(checkingItem.getCheckId());
        applyChecking.setCheckName(checkingItem.getCheckName());
        applyChecking.setFee(checkingItem.getFee());

        applyChecking.setStatus("1");

        int count = applyCheckingMapper.insertSelective(applyChecking);
        return count > 0;
    }


    /**
     * 根据病历号，查询已申请的检查
     * @param medicalId
     * @return
     */
    public List<ApplyCheckingExt> queryApplyCheckingWithMedicalId(Integer medicalId){
        return mzblMapper.queryApplyCheckingWithMedicalId(medicalId);
    }


}
