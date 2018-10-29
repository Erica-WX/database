package com.database.demo.entity;

import com.sun.istack.internal.NotNull;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class ComboUsedRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //自增
    private Integer id;

    @NotNull
    @ManyToOne(cascade={CascadeType.ALL}, fetch=FetchType.EAGER)
    @JoinColumn(name="phoneNumber",referencedColumnName="phoneNumber") // 外键设置为phoneNumber
    private User user;

    @ManyToOne(cascade={CascadeType.ALL}, fetch=FetchType.EAGER)
    @JoinColumn(name="comboId",referencedColumnName="comboId") // 外键设置为套餐id
    private Combo combo;

    private LocalDate startDateTime;

    private LocalDate endDateTime;

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

    public Combo getCombo() {
        return combo;
    }

    public void setCombo(Combo combo) {
        this.combo = combo;
    }

    public LocalDate getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(LocalDate startDateTime) {
        this.startDateTime = startDateTime;
    }

    public LocalDate getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(LocalDate endDateTime) {
        this.endDateTime = endDateTime;
    }
}
