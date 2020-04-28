package com.neuedu.service;

import com.neuedu.entity.*;
import com.neuedu.framework.HisConstants;
import com.neuedu.framework.cache.HisCache;
import com.neuedu.mapper.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 项目    ： his_all
 * 创建时间 ：2020/4/22  9:21 22
 * author  ：jshand-root
 * site    :  http://314649444.iteye.com
 * 描述     : 现场挂号的服务层
 */
@Service
public class XcghService {


    @Autowired
    ConstantsMapper constantsMapper;


    @Autowired
    DeptMapper deptMapper;


    @Autowired
    DoctorLevelMapper doctorLevelMapper;


    @Autowired
    XcghExtMapper xcghExtMapper;

    @Autowired
    MedicalRecordMapper medicalRecordMapper;

    /**
     * 初始化页面
     * @return
     */
    public Map initPage(){
        //1 性别的列表
        //从缓存中获取 性别集合
        List<Constants> xbList = HisCache.getConstantsListByConsType(HisConstants.CONSTANTS_TYP_XB);


        //2 科室列表
        DeptExample deptExample = new DeptExample();
        deptExample.createCriteria().andIsDelEqualTo(HisConstants.RECORD_IS_DEL_FALSE);//查询状态为1
        List<Dept> deptList = deptMapper.selectByExample(deptExample);

        //3 挂号级别列表
        List<DoctorLevel> levelList = doctorLevelMapper.selectByExample(null);

        //4 最新的病历号
        long maxMedicalNo = xcghExtMapper.selectMaxMedicalNo();

        Map result = new HashMap();
        result.put("maxMedicalNo",maxMedicalNo);
        result.put("xbList",xbList);
        result.put("deptList",deptList);
        result.put("levelList",levelList);

        return result;
    }


    /**
     * 挂号保存
     * @param mr 接收到 挂号问诊信息
     * @return
     */
    public boolean ghbc(MedicalRecord mr) {

        MedicalRecordWithBLOBs mrBlob = new MedicalRecordWithBLOBs();

        BeanUtils.copyProperties(mr,mrBlob);

        //设置挂号状态为待诊
        mrBlob.setStatus(HisConstants.MEDICAL_RECORD_STATUS_DZ);
        //保存之前计算一次 应收金额,根据挂号级别计算
        DoctorLevel doctorLevel = doctorLevelMapper.selectByPrimaryKey(mrBlob.getLevelId());
        BigDecimal cost = doctorLevel.getCost();
        //如果需要病历本额外加1
        if(HisConstants.YES.equals( mrBlob.getIsBook())){
            cost = cost.add(new BigDecimal(1));
        }
        mrBlob.setAmount(cost); //应收金额

        int count = medicalRecordMapper.insertSelective(mrBlob);
        return count>0;
    }


    public static void main(String[] args) {
        BigDecimal cost = new BigDecimal(100);
        BigDecimal newCost = cost.add(new BigDecimal(5));

        System.out.println(cost.intValue());
        System.out.println(newCost.intValue());

    }

    /**
     * 查询一周内挂号的数据
     * @return
     */
    public List<MedicalRecord> listMedeiacalRecord() {


        //查询一周之内的数据
        Calendar now = Calendar.getInstance();
        now.add(Calendar.DAY_OF_MONTH,-7);

        MedicalRecordExample ex = new MedicalRecordExample();
        ex.createCriteria().andCreateTimeGreaterThanOrEqualTo(now.getTime());

        return medicalRecordMapper.selectByExample(ex);
    }



    /**
     * 退号时查询一周内挂号的数据
     * @return
     */
    public List<MedicalRecord> tuiHaolistMedeiacalRecord(  MedicalRecord mr) {

        //查询一周之内的数据
        Calendar now = Calendar.getInstance();
        now.add(Calendar.DAY_OF_MONTH,-7);

        MedicalRecordExample ex = new MedicalRecordExample();
        MedicalRecordExample.Criteria c = ex.createCriteria();
        c.andCreateTimeGreaterThanOrEqualTo(now.getTime());
        //如果存在病历号，完全匹配
        if(mr.getMedicalId()!= null){
            c.andMedicalIdEqualTo(mr.getMedicalId());
        }

        //模糊查询 患者姓名 模糊查询
        if(StringUtils.isNotEmpty(mr.getName())){
            c.andNameLike("%"+mr.getName()+"%");
        }

        //模糊查询身份证号 模糊查询
        if(StringUtils.isNotEmpty(mr.getIdCard())){
            c.andIdCardLike("%"+mr.getIdCard()+"%");
        }

        return medicalRecordMapper.selectByExample(ex);
    }

    /**
     * 退号，将看诊状态改变为已退号
     * @param medicalId
     * @return
     */
    public boolean tuiHao(Integer medicalId) {
//        MedicalRecordWithBLOBs medicalRecord = medicalRecordMapper.selectByPrimaryKey(medicalId);
//        medicalRecord.setStatus(HisConstants.MEDICAL_RECORD_STATUS_TH);

        MedicalRecordWithBLOBs record = new MedicalRecordWithBLOBs();
        record.setMedicalId(medicalId);
        record.setStatus(HisConstants.MEDICAL_RECORD_STATUS_TH);


        int count = medicalRecordMapper.updateByPrimaryKeySelective(record);

        return count>0;
    }

    /**
     * 查询所有收费项目
     * @param mr
     * @return
     */
    public List feePageList(MedicalRecord mr) {
        List<FeeRecord> feeList = xcghExtMapper.selectFee(mr);
        return feeList;
    }


    /**
     * 收费操作  只改状态
     * @param applyCheckIds
     * @param applyInspectIds
     * @return
     */
    public boolean charge(Integer[] applyCheckIds, Integer[] applyInspectIds) {


        String yjfCheck = HisConstants.JCZT_YJF;  //检查状态——已收费
        String yjfInspect = HisConstants.JYZT_YJF; //检验状态——已收费

       int coutAll = 0;
       int countCheckIds = 0;
       int countInspectIds = 0;

       if(applyCheckIds!=null && applyCheckIds.length!=0){
           coutAll += xcghExtMapper.updateApplyCheckingStatus(yjfCheck,applyCheckIds);
           countCheckIds = applyCheckIds.length;
       }

        if(applyInspectIds!=null && applyInspectIds.length!=0){
            coutAll += xcghExtMapper.updateApplyInspectStatus(yjfInspect,applyInspectIds);
            countInspectIds = applyInspectIds.length;
        }

        return coutAll == (countCheckIds + countInspectIds);

    }


    /**
     * 批量退费
     * @param applyCheckIds
     * @param applyInspectIds
     * @return
     */
    public boolean refund(Integer[] applyCheckIds, Integer[] applyInspectIds) {
        String ytfCheck = HisConstants.JCZT_YTF;  //检查状态——已退费
        String ytfInspect = HisConstants.JYZT_YTF; //检验状态——已退费

        int coutAll = 0;
        int countCheckIds = 0;
        int countInspectIds = 0;

        if(applyCheckIds!=null && applyCheckIds.length!=0){
            coutAll += xcghExtMapper.updateApplyCheckingStatus(ytfCheck,applyCheckIds);
            countCheckIds = applyCheckIds.length;
        }

        if(applyInspectIds!=null && applyInspectIds.length!=0){
            coutAll += xcghExtMapper.updateApplyInspectStatus(ytfInspect,applyInspectIds);
            countInspectIds = applyInspectIds.length;
        }

        return coutAll == (countCheckIds + countInspectIds);
    }
}
