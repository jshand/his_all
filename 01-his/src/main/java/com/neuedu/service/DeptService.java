package com.neuedu.service;

import com.neuedu.entity.Dept;
import com.neuedu.entity.DeptExample;
import com.neuedu.framework.HisConstants;
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

        //查询有效的记录
        criteria.andIsDelEqualTo(HisConstants.RECORD_IS_DEL_FALSE);

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

    /**
     * 根据主键查询 部门
     * @param deptCode
     * @return
     */
    public Dept queryById(Integer deptCode) {

        return deptMapper.selectByPrimaryKey(deptCode);
    }



    /**
     * 修改部门信息
     * @param dept
     * @return
     */
    public boolean edit(Dept dept) {
        return deptMapper.updateByPrimaryKeySelective(dept) > 0 ;
    }

    /**
     * 根据主键逻辑删除 部门
     * @param deptCode
     * @return
     */
    public boolean del(Integer deptCode) {
        Dept dept = new Dept();

        dept.setDeptCode(deptCode);
        dept.setIsDel(HisConstants.RECORD_IS_DEL_TRUE); //删除的状态

        return deptMapper.updateByPrimaryKeySelective(dept) > 0 ;

    }
}
