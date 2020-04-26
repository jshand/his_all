package com.neuedu.service;

import com.neuedu.entity.CheckingItem;
import com.neuedu.entity.CheckingItemExample;
import com.neuedu.entity.InspectItem;
import com.neuedu.entity.InspectItemExample;
import com.neuedu.mapper.CheckingItemMapper;
import com.neuedu.mapper.InspectItemMapper;
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
public class JyService {

    @Autowired
    InspectItemMapper inspectItemMapper;


    /**
     *根据条件查询 检验项目
     * @param inspectItem
     * @return
     */
    public List<InspectItem> pageList(InspectItem inspectItem) {

        //    "           "    空 isNotEmpty  true
        //    "           "    空白  isNotBlank  false
        InspectItemExample em = null;
        if(StringUtils.isNotEmpty(inspectItem.getInspectName())){
            em = new InspectItemExample();
            em.createCriteria().andInspectNameLike("%"+inspectItem.getInspectName()+"%");
        }
        return inspectItemMapper.selectByExample(em);
    }
}
