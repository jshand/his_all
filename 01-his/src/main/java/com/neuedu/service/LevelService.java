package com.neuedu.service;

import com.neuedu.entity.DoctorLevel;
import com.neuedu.entity.DoctorLevelExample;
import com.neuedu.entity.User;
import com.neuedu.entity.UserExample;
import com.neuedu.framework.HisConstants;
import com.neuedu.mapper.DoctorLevelMapper;
import com.neuedu.mapper.UserMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 项目    ： his_all
 * 创建时间 ：2020/4/29  14:16 29
 * author  ：jshand-root
 * site    :  http://314649444.iteye.com
 * 描述     :
 */
@Service
public class LevelService {
    @Autowired
    UserMapper userMapper;

    @Autowired
    DoctorLevelMapper doctorLevelMapper;

    /**
     * 分页查询看诊级别信息 根据昵称查询
     * @param levelName
     * @return
     */
    public List<DoctorLevel> pageList(String levelName) {
        DoctorLevelExample example = new DoctorLevelExample();
        DoctorLevelExample.Criteria cri = example.createCriteria();

        if(StringUtils.isNotBlank(levelName)){
            cri.andLevelNameLike("%"+levelName+"%");
        }

        return doctorLevelMapper.selectByExample(example);
    }

    /**
     * 新增看诊级别
     * @param doctorLevel
     * @return
     */
    public boolean save(DoctorLevel doctorLevel) {
        return doctorLevelMapper.insertSelective(doctorLevel) > 0 ;
    }

    /**
     * 根据主键查询看诊级别
     * @param levelId
     * @return
     */
    public DoctorLevel queryById(Integer levelId) {
        return doctorLevelMapper.selectByPrimaryKey(levelId);
    }

    /**
     * 根据主键更新操作
     * @param user
     * @return
     */
    public boolean edit(DoctorLevel doctorLevel) {
        return doctorLevelMapper.updateByPrimaryKeySelective(doctorLevel) >0;
    }

    /**
     * 逻辑删除看诊级别表数据
     *
     * @param levelId
     * @return
     */
    public boolean del(Integer levelId) {

        return doctorLevelMapper.deleteByPrimaryKey(levelId) > 0 ;
    }
}
