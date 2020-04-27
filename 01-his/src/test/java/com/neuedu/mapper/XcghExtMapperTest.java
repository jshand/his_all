package com.neuedu.mapper;

import com.neuedu.HisApplication;
import com.neuedu.entity.FeeRecord;
import com.neuedu.entity.MedicalRecord;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 项目    ： his_all
 * 创建时间 ：2020/4/27  10:57 27
 * author  ：jshand-root
 * site    :  http://314649444.iteye.com
 * 描述     :
 */
@SpringBootTest(classes = HisApplication.class)
class XcghExtMapperTest {

    @Autowired
    XcghExtMapper xcghExtMapper;


    @Test
    void selectFee() {

        MedicalRecord mr = new MedicalRecord();
//        mr.setMedicalId(5);
        mr.setName("王");
        List<FeeRecord> feeList = xcghExtMapper.selectFee(mr);
        for (FeeRecord feeRecord : feeList) {
            System.out.println(feeRecord);
        }
    }
}