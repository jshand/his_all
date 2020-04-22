package com.neuedu.controller;

import com.neuedu.entity.Doctor;
import com.neuedu.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 项目    ： his_all
 * 创建时间 ：2020/4/22  10:40 22
 * author  ：jshand-root
 * site    :  http://314649444.iteye.com
 * 描述     : 医生的控制器
 */
@RestController
@RequestMapping("doctor")
public class DoctorController {

    @Autowired
    DoctorService doctorService;

    //doctor/list
    @RequestMapping("list")
    public List<Doctor> list(Integer deptCode,Integer levelId){
        return doctorService.list(deptCode,levelId);
    }




}
