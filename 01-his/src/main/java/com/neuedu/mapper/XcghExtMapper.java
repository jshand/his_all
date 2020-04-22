package com.neuedu.mapper;

/**
 * 项目    ： his_all
 * 创建时间 ：2020/4/22  11:51 22
 * author  ：jshand-root
 * site    :  http://314649444.iteye.com
 * 描述     : 现场挂号的自定义 Mapper
 */
public interface XcghExtMapper {

    /**
     * 查询最新的病历号
     * @return
     */
    public long selectMaxMedicalNo();
}
