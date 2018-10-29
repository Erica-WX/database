package com.database.demo.entity;

import com.sun.istack.internal.NotNull;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class DataRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //自增
    private Integer id;

    @NotNull
    @ManyToOne(cascade={CascadeType.ALL}, fetch=FetchType.EAGER)
    @JoinColumn(name="phoneNumber",referencedColumnName="phoneNumber") // 外键设置为phoneNumber
    private User user;

    private LocalDate startDateTime;

    private Double amount; //使用流量的兆数

    @Enumerated(value = EnumType.STRING)
    private DataType dataType; //流量类型

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

    public LocalDate getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(LocalDate startDateTime) {
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
}
