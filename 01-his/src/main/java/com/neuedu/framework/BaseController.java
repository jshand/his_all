package com.neuedu.framework;

import com.github.pagehelper.Page;

import java.util.HashMap;
import java.util.Map;

/**
 * 项目    ： his_all
 * 创建时间 ：2020/4/23  15:51 23
 * author  ：jshand-root
 * site    :  http://314649444.iteye.com
 * 描述     :
 */

public class BaseController {

    public Map ajaxSucess(boolean success){
       return ajaxSucess(success,null);
    }


    public Map ajaxSucess(boolean success,String msg){
        Map result = new HashMap();
        result.put("success",success);
        result.put("msg",msg);
        return result;
    }

    /**
     * 返回分页结果
     * @param draw
     * @param page
     * @return
     */
    public Map pageReuslt(int draw , Page page){
        Map pageResult = new HashMap();
        pageResult.put("draw",draw);
        pageResult.put("recordsTotal", page.getTotal());
        pageResult.put("recordsFiltered", page.getTotal());
        pageResult.put("data",page.getResult());

        return pageResult;
    }

}
