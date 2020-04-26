package com.neuedu.mapper;

import com.neuedu.HisApplication;
import com.neuedu.entity.ApplyCheckingExt;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 项目    ： his_all
 * 创建时间 ：2020/4/26  9:53 26
 * author  ：jshand-root
 * site    :  http://314649444.iteye.com
 * 描述     :
 */
@SpringBootTest(classes = HisApplication.class)
class MzblMapperTest {

    @Autowired
    MzblMapper mzblMapper;



    @Test
    void queryApplyCheckingWithMedicalId() {

        int medicalId = 5;
        List<ApplyCheckingExt> list = mzblMapper.queryApplyCheckingWithMedicalId(medicalId);
        for (ApplyCheckingExt applyCheckingExt : list) {
            System.out.println(applyCheckingExt);
        }
    }
}