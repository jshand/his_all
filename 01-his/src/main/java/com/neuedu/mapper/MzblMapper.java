package com.neuedu.mapper;

import com.neuedu.entity.ApplyCheckingExt;

import java.sql.PreparedStatement;
import java.util.List;

/**
 * 项目    ： his_all
 * 创建时间 ：2020/4/26  9:44 26
 * author  ：jshand-root
 * site    :  http://314649444.iteye.com
 * 描述     : 门诊病历
 */
public interface MzblMapper {

    /**
     * 根据病历号查询已申请的检查
     * @param medicalId
     * @return
     */
    public List<ApplyCheckingExt> queryApplyCheckingWithMedicalId(Integer medicalId);
}
