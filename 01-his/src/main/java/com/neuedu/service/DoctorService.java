package com.neuedu.service;

import com.neuedu.entity.DeptExample;
import com.neuedu.entity.Doctor;
import com.neuedu.entity.DoctorExample;
import com.neuedu.mapper.DoctorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 项目    ： his_all
 * 创建时间 ：2020/4/22  10:41 22
 * author  ：jshand-root
 * site    :  http://314649444.iteye.com
 * 描述     : 医生的业务层
 */
@Service
public class DoctorService {

    @Autowired
    DoctorMapper doctorMapper;

    /**
     * 查询所有医生信息
     * @param deptCode  部门编号
     * @param levelId   看诊医生的级别
     * @return
     */
    public List<Doctor> list(Integer deptCode,Integer levelId){
        DoctorExample ex = new DoctorExample();
        DoctorExample.Criteria cre = ex.createCriteria();

        cre.andDeptCodeEqualTo(deptCode);
        cre.andLevelIdEqualTo(levelId);

        return doctorMapper.selectByExample(ex);
    }


}
