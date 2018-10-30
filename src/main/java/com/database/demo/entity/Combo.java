package com.database.demo.entity;

import javax.persistence.*;
import java.util.Set;

/**
 * @author: 王轩
 * @description
 * @date: 2018/10/30
 */

@Entity
public class Combo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //自增
    private Integer comboId;

    private String comboName; // 套餐名

    private Double monthExpense; //月收费

    private Integer minuteOfCall; // 通话时长

    private Double callOverExpense; //通话超出时长费用

    private Integer numOfMessage; // 短信条数

    private Double MessageOverExpense; // 短信超出条数费用

    private Double numOfLocalData; // 本地流量兆数

    private Double localDataOverExpense; //本地流量超过兆数费用

    private Double numOfDomesticData; //国内流量兆数

    private Double domesticDataOverExpense; //国内流量超过兆数费用

    public Integer getId() {
        return comboId;
    }

    public void setId(Integer id) {
        this.comboId = id;
    }

    public String getComboName() {
        return comboName;
    }

    public void setComboName(String comboName) {
        this.comboName = comboName;
    }

    public Double getMonthExpense() {
        return monthExpense;
    }

    public void setMonthExpense(Double monthExpense) {
        this.monthExpense = monthExpense;
    }

    public Integer getMinuteOfCall() {
        return minuteOfCall;
    }

    public void setMinuteOfCall(Integer minuteOfCall) {
        this.minuteOfCall = minuteOfCall;
    }

    public Double getCallOverExpense() {
        return callOverExpense;
    }

    public void setCallOverExpense(Double callOverExpense) {
        this.callOverExpense = callOverExpense;
    }

    public Integer getNumOfMessage() {
        return numOfMessage;
    }

    public void setNumOfMessage(Integer numOfMessage) {
        this.numOfMessage = numOfMessage;
    }

    public Double getMessageOverExpense() {
        return MessageOverExpense;
    }

    public void setMessageOverExpense(Double messageOverExpense) {
        MessageOverExpense = messageOverExpense;
    }

    public Double getNumOfLocalData() {
        return numOfLocalData;
    }

    public void setNumOfLocalData(Double numOfLocalData) {
        this.numOfLocalData = numOfLocalData;
    }

    public Double getLocalDataOverExpense() {
        return localDataOverExpense;
    }

    public void setLocalDataOverExpense(Double localDataOverExpense) {
        this.localDataOverExpense = localDataOverExpense;
    }

    public Double getNumOfDomesticData() {
        return numOfDomesticData;
    }

    public void setNumOfDomesticData(Double numOfDomesticData) {
        this.numOfDomesticData = numOfDomesticData;
    }

    public Double getDomesticDataOverExpense() {
        return domesticDataOverExpense;
    }

    public void setDomesticDataOverExpense(Double domesticDataOverExpense) {
        this.domesticDataOverExpense = domesticDataOverExpense;
    }
}
