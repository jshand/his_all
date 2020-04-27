package com.neuedu.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 项目    ： his_all
 * 创建时间 ：2020/4/27  10:42 27
 * author  ：jshand-root
 * site    :  http://314649444.iteye.com
 * 描述     : 费用记录（自定义 的实体，不是跟具体某一个表关联）
 *
 *  实现Serializable 接口 ，用于在二级缓存时使用
 */
public class FeeRecord implements Serializable {

    /**
     *     ac.medical_id,
     *     ac.check_name item_name,
     *     ac.fee,
     *     ac.status,
     *     ac.create_time,
     *     '1' TYPE
     */
    private Integer applyId;
    private Integer medicalId;

    private String itemName;
    private Double fee;
    private String status;
    private Date createTime;
    private String name;
    private String idCard;

    private String type;// 费用类型，1 检查 2 检验


    public Integer getApplyId() {
        return applyId;
    }

    public void setApplyId(Integer applyId) {
        this.applyId = applyId;
    }

    public Integer getMedicalId() {
        return medicalId;
    }

    public void setMedicalId(Integer medicalId) {
        this.medicalId = medicalId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "FeeRecord{" +
                "applyId=" + applyId +
                ", medicalId=" + medicalId +
                ", itemName='" + itemName + '\'' +
                ", fee=" + fee +
                ", status='" + status + '\'' +
                ", createTime=" + createTime +
                ", name='" + name + '\'' +
                ", idCard='" + idCard + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

}
