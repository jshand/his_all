package com.neuedu.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ApplyCheckingExt extends ApplyChecking implements Serializable {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ApplyCheckingExt{" +
                "name='" + name + '\'' +
                '}'+super.toString();
    }
}