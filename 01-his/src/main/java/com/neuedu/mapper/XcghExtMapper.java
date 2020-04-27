package com.neuedu.mapper;

import com.neuedu.entity.FeeRecord;
import com.neuedu.entity.MedicalRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 项目    ： his_all
 * 创建时间 ：2020/4/22  11:51 22
 * author  ：jshand-root
 * site    :  http://314649444.iteye.com
 * 描述     : 现场挂号的自定义 Mapper
 */
public interface XcghExtMapper {

    /**
     * 查询最新的病历号
     * @return
     */
    public long selectMaxMedicalNo();


    /**
     * 根据病历相关信息，查询所有收费项目
     * @param mr
     * @return
     */
    public List<FeeRecord> selectFee(MedicalRecord mr);


    /**
     * 更新申请检查的状态
     * @param applyCheckIds
     * @return
     */
    public int updateApplyCheckingStatus(@Param(value="status") String status , @Param(value="applyCheckIds") Integer[] applyCheckIds);


    /**
     * 更新申请检化验的状态
     * @param applyInspectIds
     * @return
     */
    public int updateApplyInspectStatus(@Param(value="status") String status ,@Param(value="applyInspectIds")Integer[] applyInspectIds);



}
