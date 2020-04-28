package com.neuedu.framework;

/**
 * 项目    ： his_all
 * 创建时间 ：2020/4/21  9:21 21
 * author  ：jshand-root
 * site    :  http://314649444.iteye.com
 * 描述     : 常量
 */
public class HisConstants {

    //登录的状态
    public static final String LOGIN_USER = "LoginUser";

    //登录的验证码
    public static final String VERIFY_CODE = "verifyCode";


    //是否
    public static final String YES = "Y";
    public static final String NO = "N";

    //表中记录是否删除的状态  1 正常，0已删除
    public static final String RECORD_IS_DEL_FALSE = "1";
    public static final String RECORD_IS_DEL_TRUE = "0";


    //常数项表的类别：性别
    public static final String CONSTANTS_TYP_XB = "XB";


    //病历问诊状态  状态:待诊、已接诊、已问诊、诊毕、 退号
    public static final String MEDICAL_RECORD_STATUS_DZ = "1";
    public static final String MEDICAL_RECORD_STATUS_YJZ = "2";
    public static final String MEDICAL_RECORD_STATUS_YWZ = "3";
    public static final String MEDICAL_RECORD_STATUS_YZB = "4";
    public static final String MEDICAL_RECORD_STATUS_TH  = "5";


    /**
     * 检查状态
     */
    public static final String JCZT_DJF  = "1";  //代缴费
    public static final String JCZT_YJF  = "2";  //已缴费
    public static final String JCZT_YJC  = "3";  //已检查
    public static final String JCZT_YTF  = "4";  //已退费


    /**
     * 检验状态
     */
    public static final String JYZT_DJF  = "1";  //代缴费
    public static final String JYZT_YJF  = "2";  //已缴费
    public static final String JYZT_YJC  = "3";  //已检查
    public static final String JYZT_YTF  = "4";  //已退费
}
