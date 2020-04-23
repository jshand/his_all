package com.neuedu.framework;

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
        Map result = new HashMap();
        result.put("success",success);
        return result;
    }

}
