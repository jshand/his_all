package com.neuedu.service;

import com.neuedu.entity.Constants;
import com.neuedu.entity.ConstantsExample;
import com.neuedu.entity.ConstantsType;
import com.neuedu.entity.ConstantsTypeExample;
import com.neuedu.mapper.ConstantsMapper;
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

    @Autowired
    ConstantsMapper constantsMapper;


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


    public List<Constants> queryConstantsByPage(Constants constants) {
        ConstantsExample ex = new ConstantsExample();
        ConstantsExample.Criteria cre = ex.createCriteria(); // ()


        if(StringUtils.isNotBlank(constants.getConsType())){
            cre.andConsTypeLike("%"+constants.getConsType()+"%");  // and code like '%xx%';
        }
        if(StringUtils.isNotBlank(constants.getConsCode())){
            cre.andConsCodeLike("%"+constants.getConsCode()+"%");  // and code like '%xx%';
        }

        if(StringUtils.isNotBlank(constants.getConsName())){
            cre.andConsNameLike("%"+constants.getConsName()+"%");  // and code like '%xx%';
        }

        ex.setOrderByClause(" cons_type ");

        return constantsMapper.selectByExample(ex);
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

    public boolean constantsTypeDel(Integer typeId) throws Exception {

        ConstantsType constantsType = constantsTypeMapper.selectByPrimaryKey(typeId);

        //如果存在常量数据，不能删除，抛异常
        ConstantsExample constantsEx = new ConstantsExample();
        constantsEx.createCriteria().andConsTypeEqualTo(constantsType.getCode());
        long count = constantsMapper.countByExample(constantsEx);
        if(count>0){
            throw new Exception("存在常量数据，不能删除");
        }

        //可以删除
        int delCount = constantsTypeMapper.deleteByPrimaryKey(typeId);

        return delCount>0;
    }

    /**
     * 根据主键查询常数类别
     * @param typeId
     * @return
     */
    public ConstantsType constantsTypeQuery(Integer typeId) {
        return constantsTypeMapper.selectByPrimaryKey(typeId);
    }


    /**
     * 根据主键查询常数类别
     * @param consId
     * @return
     */
    public Constants constantsQuery(Integer consId) {
        return constantsMapper.selectByPrimaryKey(consId);
    }

    /**
     * 修改 常数类别
     * @param typeId
     * @param code
     * @param name
     * @return
     */
    public boolean editType(Integer typeId, String code, String name) {

        ConstantsType constantType = new ConstantsType();
        constantType.setTypeId(typeId);
        constantType.setCode(code);
        constantType.setName(name);

        return constantsTypeMapper.updateByPrimaryKey(constantType) >0;
    }

    /**
     * 保存常数项信息
     * @param constants
     * @return
     */
    public boolean saveConstants(Constants constants) {
        return constantsMapper.insertSelective(constants) >0;
    }



    /**
     * 修改常数项信息
     * @param constants
     * @return
     */
    public boolean editConstants(Constants constants) {
        return constantsMapper.updateByPrimaryKeySelective(constants) >0;
    }



    /**
     * 删除常数项信息
     * @param consId
     * @return
     */
    public boolean constantsDel(Integer consId) {

        return constantsMapper.deleteByPrimaryKey(consId) >0;
    }
}
