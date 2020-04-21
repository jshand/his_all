package com.neuedu.mapper;

import com.neuedu.entity.MedicalRecord;
import com.neuedu.entity.MedicalRecordExample;
import com.neuedu.entity.MedicalRecordWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MedicalRecordMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_medical_record
     *
     * @mbg.generated Tue Apr 21 09:05:41 CST 2020
     */
    long countByExample(MedicalRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_medical_record
     *
     * @mbg.generated Tue Apr 21 09:05:41 CST 2020
     */
    int deleteByExample(MedicalRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_medical_record
     *
     * @mbg.generated Tue Apr 21 09:05:41 CST 2020
     */
    int deleteByPrimaryKey(Integer medicalId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_medical_record
     *
     * @mbg.generated Tue Apr 21 09:05:41 CST 2020
     */
    int insert(MedicalRecordWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_medical_record
     *
     * @mbg.generated Tue Apr 21 09:05:41 CST 2020
     */
    int insertSelective(MedicalRecordWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_medical_record
     *
     * @mbg.generated Tue Apr 21 09:05:41 CST 2020
     */
    List<MedicalRecordWithBLOBs> selectByExampleWithBLOBs(MedicalRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_medical_record
     *
     * @mbg.generated Tue Apr 21 09:05:41 CST 2020
     */
    List<MedicalRecord> selectByExample(MedicalRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_medical_record
     *
     * @mbg.generated Tue Apr 21 09:05:41 CST 2020
     */
    MedicalRecordWithBLOBs selectByPrimaryKey(Integer medicalId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_medical_record
     *
     * @mbg.generated Tue Apr 21 09:05:41 CST 2020
     */
    int updateByExampleSelective(@Param("record") MedicalRecordWithBLOBs record, @Param("example") MedicalRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_medical_record
     *
     * @mbg.generated Tue Apr 21 09:05:41 CST 2020
     */
    int updateByExampleWithBLOBs(@Param("record") MedicalRecordWithBLOBs record, @Param("example") MedicalRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_medical_record
     *
     * @mbg.generated Tue Apr 21 09:05:41 CST 2020
     */
    int updateByExample(@Param("record") MedicalRecord record, @Param("example") MedicalRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_medical_record
     *
     * @mbg.generated Tue Apr 21 09:05:41 CST 2020
     */
    int updateByPrimaryKeySelective(MedicalRecordWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_medical_record
     *
     * @mbg.generated Tue Apr 21 09:05:41 CST 2020
     */
    int updateByPrimaryKeyWithBLOBs(MedicalRecordWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_medical_record
     *
     * @mbg.generated Tue Apr 21 09:05:41 CST 2020
     */
    int updateByPrimaryKey(MedicalRecord record);
}