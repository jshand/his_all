package com.neuedu.controller;

import com.neuedu.entity.MedicalRecord;
import com.neuedu.service.XcghService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 项目    ： his_all
 * 创建时间 ：2020/4/22  9:20 22
 * author  ：jshand-root
 * site    :  http://314649444.iteye.com
 * 描述     : 现场挂号
 */
@RestController
@RequestMapping("xcgh")
public class XcghController {

    @Autowired
    XcghService xcghService;


    /**
     * 现场挂号初始化页面 中的下拉选
     *  xcgh/initPage
     * @return
     */
    @RequestMapping("initPage")
    public Map initPage(){
        return xcghService.initPage();
    }


    /**
     * 挂号保存
     * @param mr
     * @return
     */
    @RequestMapping("ghbc")
    public Map ghbc(MedicalRecord  mr){

        boolean success = xcghService.ghbc(mr);


        Map result = new HashMap();
        result.put("success",success);

        return result;
    }


}
