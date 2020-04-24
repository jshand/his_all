package com.neuedu.service;

import com.neuedu.entity.CheckingItem;
import com.neuedu.entity.CheckingItemExample;
import com.neuedu.mapper.CheckingItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 项目    ： his_all
 * 创建时间 ：2020/4/24  15:06 24
 * author  ：jshand-root
 * site    :  http://314649444.iteye.com
 * 描述     :
 */
@Service
public class JcService {

    @Autowired
    CheckingItemMapper checkingItemMapper;


    /**
     *根据条件查询 检查项目
     * @param checkingItem
     * @return
     */
    public List<CheckingItem> pageList(CheckingItem checkingItem) {

        return checkingItemMapper.selectByExample(null);
    }
}
