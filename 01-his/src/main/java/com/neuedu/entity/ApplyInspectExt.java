package com.neuedu.entity;

/**
 * 项目    ： his_all
 * 创建时间 ：2020/4/26  14:58 26
 * author  ：jshand-root
 * site    :  http://314649444.iteye.com
 * 描述     :
 */
public class ApplyInspectExt extends ApplyInspect {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ApplyInspectItem{" +
                "name='" + name + '\'' +
                '}'+super.toString();
    }
}
