package com.neuedu.service;

import com.neuedu.entity.Dept;
import com.neuedu.entity.DeptExample;
import com.neuedu.mapper.DeptMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 项目    ： his_all
 * 创建时间 ：2020/4/29  9:33 29
 * author  ：jshand-root
 * site    :  http://314649444.iteye.com
 * 描述     :
 */
@Service
public class DeptService {

    @Autowired
    DeptMapper deptMapper;


    /**
     * 根据条件查询部门列表
     * @param dept
     * @return
     */
    public List<Dept> pageList(Dept dept) {

        DeptExample ex = new DeptExample();
        DeptExample.Criteria criteria = ex.createCriteria();//where ()
        if(dept.getDeptCode() != null){
            criteria.andDeptCodeEqualTo(dept.getDeptCode());// and dept_code = ?
        }
        if(StringUtils.isNotBlank(dept.getDeptName())){
            criteria.andDeptNameLike("%"+dept.getDeptName()+"%");  // and dept_name like '%?%'
        }
        return deptMapper.selectByExample(ex);
    }

    /**
     * 保存部门信息
     * @param dept
     * @return
     */
    public boolean save(Dept dept) {
        return deptMapper.insertSelective(dept) >0 ;
    }
}
