package com.neuedu.service;

import com.neuedu.entity.ConstantsType;
import com.neuedu.entity.ConstantsTypeExample;
import com.neuedu.mapper.ConstantsTypeMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 项目    ： his_all
 * 创建时间 ：2020/4/28  11:28 28
 * author  ：jshand-root
 * site    :  http://314649444.iteye.com
 * 描述     :
 */
@Service
public class ConstantsService {

    @Autowired
    ConstantsTypeMapper constantsTypeMapper;


    public List<ConstantsType> queryConstantsTypeByPage(ConstantsType constantsType) {
        ConstantsTypeExample ex = new ConstantsTypeExample();
        ConstantsTypeExample.Criteria cre = ex.createCriteria(); // ()
        if(StringUtils.isNotBlank(constantsType.getCode())){
            cre.andCodeLike("%"+constantsType.getCode()+"%");  // and code like '%xx%';
        }
        if(StringUtils.isNotBlank(constantsType.getName())){
            cre.andNameLike("%" + constantsType.getName() + "%");  // and code like '%xx%';
        }
        return constantsTypeMapper.selectByExample(ex);
    }

    /**
     * 保存 常数类别（代码、名称）
     * @param code
     * @param name
     * @return
     */
    public boolean saveType(String code, String name) {
        ConstantsType record = new ConstantsType();
        record.setCode(code);
        record.setName(name);
        int count = constantsTypeMapper.insertSelective(record);
        return count>0;
    }
}
