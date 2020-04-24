package com.neuedu.framework.cache;

import com.neuedu.entity.Constants;
import com.neuedu.mapper.ConstantsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 项目    ： his_all
 * 创建时间 ：2020/4/24  9:20 24
 * author  ：jshand-root
 * site    :  http://314649444.iteye.com
 * 描述     : 系统缓存
 */
@Component("hisCache")
public class HisCache {


    /**
     * constants:Constants [Hash = 1543887290, consId=1, consType=XB, consCode=1, consName=男, serialVersionUID=1]
     * constants:Constants [Hash = 933787991, consId=2, consType=XB, consCode=2, consName=女, serialVersionUID=1]
     * constants:Constants [Hash = 1508425766, consId=3, consType=YN, consCode=Y, consName=是, serialVersionUID=1]
     * constants:Constants [Hash = 1194601314, consId=4, consType=YN, consCode=N, consName=否, serialVersionUID=1]
     */
    //自定义所有常数项集合
    private static  List<Constants> list = null;


    /**
     *  cache{
     *      "XB":[
     *          constants:Constants [Hash = 1543887290, consId=1, consType=XB, consCode=1, consName=男, serialVersionUID=1]
     *      * constants:Constants [Hash = 933787991, consId=2, consType=XB, consCode=2, consName=女, serialVersionUID=1]
     *      ]
     *      ,
     *      "YN":[
     *          constants:Constants [Hash = 1508425766, consId=3, consType=YN, consCode=Y, consName=是, serialVersionUID=1]
     *      *   constants:Constants [Hash = 1194601314, consId=4, consType=YN, consCode=N, consName=否, serialVersionUID=1]
     *      ]
     *  }
     */
    private static Map<String,List> cache = new HashMap();


    /**
     * XB_1  男
     * XB_2  女
     *
     * YN_Y  是
     * YN_N  否
     *
     * HZZT_1  待诊
     *
     *
     *
     *
     */
    private static Map<String,String> jsonCache = new HashMap();



    /**
     * 根据常数项类表获取具体对应的常数项
     * @param consType  XB    YN
     * @return
     */
    public static List<Constants> getConstantsListByConsType(String consType){
        List<Constants> result = new ArrayList();

        if(cache.containsKey(consType)){//如果缓存有数据
            result =  cache.get(consType);
        }else{
            for (Constants constants : list) {
                if(consType.equals(constants.getConsType())){
                    result.add(constants);
                }
            }
            cache.put(consType,result);
        }

        return result;
    }




    @Autowired
    ConstantsMapper constantsMapper;

    /**
     * 初始化缓存
     * @return
     */
    @PostConstruct
    public void initCache(){
        System.out.println("-----------初始化缓存...-------------");
        //在系统启动时初始化 常数项 1 男  2女
        list = constantsMapper.selectByExample(null);

        for (Constants constants : list) {
            /**
             * XB_1  男
             * XB_2  女
             */
            String key = constants.getConsType()+"_"+constants.getConsCode();
            String value = constants.getConsName();
            jsonCache.put(key,value);
        }


    }


    /**
     * 清空缓存的方法
     */
    public void clear(){
        cache.clear(); //清空Map

        list.clear();

        jsonCache.clear();

        initCache(); //重新查询常量数据
    }


    /**
     * 获取json格式的 常量缓存
     * @return
     */
    public static Map<String, String> getJsonCache() {
        return jsonCache;
    }
}
