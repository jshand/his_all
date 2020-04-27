package com.neuedu.service;

import com.neuedu.entity.*;
import com.neuedu.framework.HisConstants;
import com.neuedu.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
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

    @Autowired
    InspectItemMapper inspectItemMapper;

    @Autowired
    ApplyInspectMapper applyInspectMapper;

    /**
     * 查询病历信息 查询当前看诊时间的
     * 根据医生工号
     * @return
     */
    public List<MedicalRecordWithBLOBs> queryMedicalRecord(Integer docId) {

        MedicalRecordExample ex1 = new MedicalRecordExample();
        ex1.createCriteria().
                andStatusEqualTo(HisConstants.MEDICAL_RECORD_STATUS_DZ).
                andDocIdEqualTo(docId);
        // yyyy-MM-dd    new Date().formart("yyy-MM-dd")
//                andVisitingTimeEqualTo(new Date());

        //TODO ,查询病历时需要查询 当天的看诊
        List<MedicalRecordWithBLOBs> dzList = medicalRecordMapper.selectByExampleWithBLOBs(ex1);


        MedicalRecordExample ex2 = new MedicalRecordExample();
        ex2.createCriteria().
                andStatusNotEqualTo(HisConstants.MEDICAL_RECORD_STATUS_DZ).
                andDocIdEqualTo(docId);
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

        int count = sq(medicalId,checkId);

        return count > 0;
    }


    /**
     * 申请一条检查
     * @param medicalId
     * @param checkId
     * @return
     */
    public int sq(Integer medicalId, Integer checkId){
        CheckingItem checkingItem = checkingItemMapper.selectByPrimaryKey(checkId);


        ApplyChecking applyChecking = new ApplyChecking();
        applyChecking.setMedicalId(medicalId);

        applyChecking.setCheckId(checkingItem.getCheckId());
        applyChecking.setCheckName(checkingItem.getCheckName());
        applyChecking.setFee(checkingItem.getFee());

        applyChecking.setStatus(HisConstants.JCZT_DJF);

        int count = applyCheckingMapper.insertSelective(applyChecking);

        return count;
    }



    /**
     * 根据病历号，查询已申请的检查
     * @param medicalId
     * @return
     */
    public List<ApplyCheckingExt> queryApplyCheckingWithMedicalId(Integer medicalId){
        return mzblMapper.queryApplyCheckingWithMedicalId(medicalId);
    }

    /**
     * 批量申请
     * @param medicalId
     * @param checkIds
     * @return
     */
    public boolean plsqjc(Integer medicalId, Integer[] checkIds) {
        int count = 0;
        for (Integer checkId : checkIds) {
            count += sq(medicalId,checkId);
        }

        return checkIds.length == count;
    }

     /**
     * 申请检验
     * @param medicalId
     * @param inspectId
     * @return
     */
    public boolean sqjy(Integer medicalId, Integer inspectId) {

        int count = sqJyByOne(medicalId,inspectId);

        return count > 0;
    }



    /**
     * 申请一条检验
     * @param medicalId
     * @param inspectId
     * @return
     */
    public int sqJyByOne(Integer medicalId, Integer inspectId){
        InspectItem inspectItem = inspectItemMapper.selectByPrimaryKey(inspectId);


        //待检验项目
        ApplyInspect applyInspect = new ApplyInspect();
        applyInspect.setMedicalId(medicalId);

        applyInspect.setInspectId(inspectItem.getInspectId());
        applyInspect.setInspectName(inspectItem.getInspectName());
        applyInspect.setFee(inspectItem.getFee());

        applyInspect.setStatus(HisConstants.JYZT_DJF);//

        int count = applyInspectMapper.insertSelective(applyInspect);

        return count;
    }


    public List<ApplyInspectExt> queryApplyInspectWithMedicalId(Integer medicalId){
        return mzblMapper.queryApplyInspectWithMedicalId(medicalId);
    }
}
