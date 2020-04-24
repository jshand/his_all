package com.neuedu.controller;

import com.neuedu.framework.BaseController;
import com.neuedu.framework.cache.HisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 项目    ： his_all
 * 创建时间 ：2020/4/24  9:47 24
 * author  ：jshand-root
 * site    :  http://314649444.iteye.com
 * 描述     : 系统配置控制器
 */
@RestController
@RequestMapping("hisconfig")
public class HisConfigController extends BaseController {
    @Autowired
    HisCache cache;


    /**
     * 清空系统缓存
     * http://127.0.0.1/hisconfig/clearCahce
     * @return
     */
    @RequestMapping("clearCahce")
    public Map clearCahce(){
        cache.clear();
        return super.ajaxSucess(true);
    }


}
