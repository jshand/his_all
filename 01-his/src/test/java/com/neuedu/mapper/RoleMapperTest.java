package com.neuedu.mapper;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.neuedu.HisApplication;
import com.neuedu.entity.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 项目    ： his_all
 * 创建时间 ：2020/4/17  15:08 17
 * author  ：jshand-root
 * site    :  http://314649444.iteye.com
 * 描述     :
 */
@SpringBootTest(classes = HisApplication.class)
class RoleMapperTest {

    @Autowired
    RoleMapper roleMapper;


    @Test
    void insert() {

        for (int i = 0; i < 10 ; i++) {
            Role role = new Role();
            role.setRoleName("门诊医生"+i);
            int count = roleMapper.insertSelective(role);
            System.out.println("count:"+count);
        }

    }

    @Test
    void selectByExample() {


        int pageNum = 1;
        int pageSize = 4;//0 1 2 3       4 5 6 7     8 9

        Page<Role> page = PageHelper.startPage(pageNum, pageSize);
        List<Role> list = roleMapper.selectByExample(null);

//        System.out.println(list.size());
        System.out.println("当前页号\t"+pageNum);
        System.out.println("每页显示数量\t"+pageSize);
        System.out.println("总条数\t"+page.getTotal());
        System.out.println("总页数\t"+page.getPages());

        System.out.println("_______________________________");
        for (Role role : list) {
            System.out.println(role);
        }


    }
}