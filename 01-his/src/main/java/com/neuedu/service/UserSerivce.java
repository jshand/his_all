package com.neuedu.service;

import com.neuedu.entity.Dept;
import com.neuedu.entity.User;
import com.neuedu.entity.UserExample;
import com.neuedu.framework.HisConstants;
import com.neuedu.mapper.UserMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 项目    ： his_all
 * 创建时间 ：2020/4/29  11:33 29
 * author  ：jshand-root
 * site    :  http://314649444.iteye.com
 * 描述     :
 */
@Service
public class UserSerivce {
    @Autowired
    UserMapper userMapper;

    /**
     * 分页查询用户信息 根据昵称查询
     * @param user
     * @return
     */
    public List<User> pageList(User user) {
        UserExample example = new UserExample();
        UserExample.Criteria cri = example.createCriteria().andIsDelEqualTo(HisConstants.RECORD_IS_DEL_FALSE);

        if(StringUtils.isNotBlank(user.getDisplayName())){
            cri.andDisplayNameLike("%"+user.getDisplayName()+"%");
        }

        return userMapper.selectByExample(example);
    }

    /**
     * 新增用户
     * @param user
     * @return
     */
    public boolean save(User user) {
        return userMapper.insertSelective(user) > 0 ;
    }

    /**
     * 根据主键查询用户
     * @param userId
     * @return
     */
    public User queryById(Integer userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    /**
     * 根据主键更新操作
     * @param user
     * @return
     */
    public boolean edit(User user) {
        return userMapper.updateByPrimaryKeySelective(user) >0;
    }

    /**
     * 逻辑删除用户表数据
     *
     * @param userId
     * @return
     */
    public boolean del(Integer userId) {
        User user = new User();

        user.setUserId(userId);
        user.setIsDel(HisConstants.RECORD_IS_DEL_TRUE); //删除的状态

        return userMapper.updateByPrimaryKeySelective(user) > 0 ;
    }
}
