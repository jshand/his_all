package com.neuedu.service;

import com.neuedu.entity.ConstantsExample;
import com.neuedu.entity.Dept;
import com.neuedu.entity.DeptExample;
import com.neuedu.entity.DoctorLevel;
import com.neuedu.framework.HisConstants;
import com.neuedu.mapper.ConstantsMapper;
import com.neuedu.mapper.DeptMapper;
import com.neuedu.mapper.DoctorLevelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    /**
     * 初始化页面
     * @return
     */
    public Map initPage(){
        //1 性别的列表
        //TODO 应该在初始化的时候 查询
        ConstantsExample conExample = new ConstantsExample();
        conExample.createCriteria().andConsTypeEqualTo(HisConstants.CONSTANTS_TYP_XB);
        List xbList = constantsMapper.selectByExample(conExample);

        //2 科室列表
        DeptExample deptExample = new DeptExample();
        deptExample.createCriteria().andIsDelEqualTo(HisConstants.RECORD_IS_DEL_FALSE);//查询状态为1
        List<Dept> deptList = deptMapper.selectByExample(deptExample);

        //3 挂号级别列表
        List<DoctorLevel> levelList = doctorLevelMapper.selectByExample(null);


        Map result = new HashMap();
        result.put("xbList",xbList);
        result.put("deptList",deptList);
        result.put("levelList",levelList);

        return result;
    }



}
