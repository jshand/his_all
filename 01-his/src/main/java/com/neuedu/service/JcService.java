package com.neuedu.service;

import com.neuedu.entity.CheckingItem;
import com.neuedu.entity.CheckingItemExample;
import com.neuedu.mapper.CheckingItemMapper;
import org.apache.commons.lang3.StringUtils;
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

        //    "           "    空 isNotEmpty  true
        //    "           "    空白  isNotBlank  false
        CheckingItemExample em = null;
        if(StringUtils.isNotEmpty(checkingItem.getCheckName())){
            em = new CheckingItemExample();
            em.createCriteria().andCheckNameLike("%"+checkingItem.getCheckName()+"%");
        }
        return checkingItemMapper.selectByExample(em);
    }
}
