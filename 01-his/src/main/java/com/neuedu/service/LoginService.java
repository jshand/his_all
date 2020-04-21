package com.neuedu.service;

import com.neuedu.entity.User;
import com.neuedu.entity.UserExample;
import com.neuedu.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 项目    ： his_all
 * 创建时间 ：2020/4/21  9:11 21
 * author  ：jshand-root
 * site    :  http://314649444.iteye.com
 * 描述     :
 */
@Service
public class LoginService {

    @Autowired
    UserMapper userDao;

    /**
     * 根据用户名密码查询，是否存在相同的用户
     * @param userName
     * @param password
     * @return
     */
    public User login(String userName, String password){
        UserExample example = new UserExample();
        example.createCriteria().andUserNameEqualTo(userName).andPasswordEqualTo(password);
        List<User> list =  userDao.selectByExample(example);
        //如果根据用户名密码、能够查询到信息，则登录成功
        if(list != null && list.size()>0){
            return list.get(0);
        }else{
            return null;
        }
    }
}
