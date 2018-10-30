package com.database.demo.entity;

import com.sun.istack.internal.NotNull;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author: 王轩
 * @description
 * @date: 2018/10/30
 */

@Entity
public class DataRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //自增
    private Integer id;

    @NotNull
    @ManyToOne(cascade={CascadeType.MERGE}, fetch=FetchType.EAGER)
    @JoinColumn(name="phoneNumber",referencedColumnName="phoneNumber") // 外键设置为phoneNumber
    private User user;

    private LocalDateTime startDateTime;

    private Double amount; //使用流量的兆数

    @Enumerated(value = EnumType.STRING)
    private DataType dataType; //流量类型

    private Double expense;

    public DataRecord() {
    }

    public DataRecord(User user, LocalDateTime startDateTime, Double amount, DataType dataType, Double expense) {
        this.user = user;
        this.startDateTime = startDateTime;
        this.amount = amount;
        this.dataType = dataType;
        this.expense = expense;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public DataType getDataType() {
        return dataType;
    }

    public void setDataType(DataType dataType) {
        this.dataType = dataType;
    }

    public Double getExpense() {
        return expense;
    }

    public void setExpense(Double expense) {
        this.expense = expense;
    }
}
