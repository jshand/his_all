package com.neuedu.mapper;

import com.neuedu.HisApplication;
import com.neuedu.entity.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

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


    @Autowired
    DoctorLevelMapper doctorLevelMapper;
    /**
     * 准备挂号级别（专家诊、普通诊）
     */
    @Test
    void pdDoctorLevel() {
        DoctorLevel l1 = new DoctorLevel();
        l1.setLevelName("普通诊");
        l1.setCost(new BigDecimal(8));
        doctorLevelMapper.insertSelective(l1);

        DoctorLevel l2 = new DoctorLevel();
        l2.setLevelName("专家诊");
        l2.setCost(new BigDecimal(100));
        doctorLevelMapper.insertSelective(l2);
    }



    @Autowired
    DoctorMapper doctorMapper;
    /**
     * 准备医生的信息
     */
    @Test
    void pdDoctor() {
        Doctor d1 = new Doctor();
        d1.setDeptCode(1);
        d1.setDocName("王旭");
        d1.setGender("2");
        d1.setLevelId(2);



        Doctor d2 = new Doctor();
        d2.setDeptCode(1);
        d2.setDocName("李姝明");
        d2.setGender("1");
        d2.setLevelId(1);


        Doctor d3 = new Doctor();
        d3.setDeptCode(11);
        d3.setDocName("李建伟");
        d3.setGender("1");
        d3.setLevelId(1);


        Doctor d4 = new Doctor();
        d4.setDeptCode(18);
        d4.setDocName("王雨欣");
        d4.setGender("2");
        d4.setLevelId(2);

        doctorMapper.insertSelective(d1);
        doctorMapper.insertSelective(d2);
        doctorMapper.insertSelective(d3);
        doctorMapper.insertSelective(d4);






    }
}