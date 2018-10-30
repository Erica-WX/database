package com.database.demo.service;

import com.database.demo.entity.Combo;
import com.database.demo.entity.ComboUsedRecord;
import com.database.demo.entity.DataType;

import java.util.List;

/**
 * @author: 王轩
 * @description
 * @date: 2018/10/30
 */

public interface ExamineService {

    /**
     * 套餐查询
     * @param phoneNumber
     * @return
     */
    List<ComboUsedRecord> getAllCombos(String phoneNumber);

    /**
     * 订购某个套餐
     * @param phoneNumber
     * @param comboId
     * @param months // 套餐使用时长
     */
    void setNewCombo(String phoneNumber,Integer comboId, int months);

    /**
     * 退订某个套餐
     * @param phoneNumber
     * @param comboId
     * @param nextMonth 是否次月生效
     */
    void cancelOneCombo(String phoneNumber,Integer comboId,boolean nextMonth);

    /**
     * 通话情况下的资费生成
     * @param phoneNumber
     * @return
     */
    double getExpenseOnCallCombo(String phoneNumber);

    /**
     * 使用流量情况下的资费生成
     * @param phoneNumber
     * @return
     */
    double getExpenseOnDataCombo(String phoneNumber);

    /**
     * 生成月账单
     * @param phoneNumber
     */
    void getMonthBill(String phoneNumber);

    void setMessageRecord(String phoneNumber, int num);

    void setCallRecord(String phoneNumber, int calls, int minute);

    void setDataRecord(String phoneNumber, int num, double amount, DataType type);
}
