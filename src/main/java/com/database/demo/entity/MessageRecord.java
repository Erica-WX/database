package com.database.demo.entity;

import com.sun.istack.internal.NotNull;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class MessageRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //自增
    private Integer id;

    @NotNull
    @ManyToOne(cascade={CascadeType.ALL}, fetch=FetchType.EAGER)
    @JoinColumn(name="phoneNumber",referencedColumnName="phoneNumber") // 外键设置为phoneNumber
    private User user;

    private LocalDate startDateTime;

    private Double expense;

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

    public Double getExpense() {
        return expense;
    }

    public void setExpense(Double expense) {
        this.expense = expense;
    }
}
