package com.neuedu.mapper;

import com.neuedu.HisApplication;
import com.neuedu.entity.Constants;
import com.neuedu.entity.ConstantsType;
import com.neuedu.entity.Dept;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 项目    ： his_all
 * 创建时间 ：2020/4/21  14:42 21
 * author  ：jshand-root
 * site    :  http://314649444.iteye.com
 * 描述     :
 */
@SpringBootTest(classes = HisApplication.class)
class BaseDateMapperTest {

    @Autowired
    ConstantsTypeMapper constantsTypeMapper;
    @Autowired
    ConstantsMapper constantsMapper;

    /**
     * 准备常量类别数据
     */
    @Test
    void pdConstantsType() {
        ConstantsType ct = new ConstantsType();
        ct.setCode("XB");
        ct.setName("性别码");


        ConstantsType ctYn = new ConstantsType();
        ctYn.setCode("YN");
        ctYn.setName("是否");

        constantsTypeMapper.insertSelective(ct);
        constantsTypeMapper.insertSelective(ctYn);
    }


    /**
     * 准备常量类别数据
     */
    @Test
    void pdConstants() {
        Constants constants1 = new Constants();

        //性别的信息
        constants1.setConsType("XB");
        constants1.setConsCode("1");
        constants1.setConsName("男");

        Constants constants2 = new Constants();
        constants2.setConsType("XB");
        constants2.setConsCode("2");
        constants2.setConsName("女");


        //性别的信息
        constants1.setConsType("XB");
        constants1.setConsCode("1");
        constants1.setConsName("男");

        Constants constants3 = new Constants();
        constants3.setConsType("YN");
        constants3.setConsCode("Y");
        constants3.setConsName("是");

        Constants constants4 = new Constants();
        constants4.setConsType("YN");
        constants4.setConsCode("N");
        constants4.setConsName("否");

        constantsMapper.insertSelective(constants1);
        constantsMapper.insertSelective(constants2);
        constantsMapper.insertSelective(constants3);
        constantsMapper.insertSelective(constants4);

    }


    @Autowired
    DeptMapper deptMapper;

    @Test
    void pdDept() {

        String[][] depts = {
                {"BTFYK", "变态反应科"},
                {"FSMYNK", "风湿免疫内科"},
                {"GCWK", "肛肠外科"},
                {"GDWK", "肝胆外科"},
                {"GRNK", "感染内科"},
                {"HXNK", "呼吸内科"},
                {"LNBNK", "老年病内科"},
                {"MNWK", "泌尿外科"},
                {"NFMK", "内分泌科"},
                {"PTNK", "普通内科"},
                {"PTWK", "普通外科"},
                {"QGYZ", "器官移植"},
                {"RXWK", "乳腺外科"},
                {"SBNK", "肾病内科"},
                {"SJNK", "神经内科"},
                {"SJWK", "神经外科"},
                {"TXK", "透析科"},
                {"XHNK", "消化内科"},
                {"XWK", "胸外科"},
                {"XXGNK", "心血管内科"},
                {"XXGWK", "心血管外科"},
                {"XYNK", "血液内科"},
                {"XZWK", "心脏外科"},
                {"ZXWK", "整形外科"}

        };
        int num = 0;
        for (String[] deptArray : depts) {
            Dept dept = new Dept();
            dept.setDeptCode(++num);
            dept.setDeptName(deptArray[1]);
            dept.setDeptType("1"); //1 治疗科室、2 辅助科室
            deptMapper.insertSelective(dept);
        }

    }
}