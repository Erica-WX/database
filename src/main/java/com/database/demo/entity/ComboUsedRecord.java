package com.database.demo.entity;

import com.sun.istack.internal.NotNull;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author: 王轩
 * @description
 * @date: 2018/10/30
 */

@Entity
public class ComboUsedRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //自增
    private Integer id;

    @NotNull
    @ManyToOne(cascade={CascadeType.MERGE}, fetch=FetchType.EAGER)
    @JoinColumn(name="phoneNumber",referencedColumnName="phoneNumber") // 外键设置为phoneNumber
    private User user;

    @ManyToOne(cascade={CascadeType.MERGE}, fetch=FetchType.EAGER)
    @JoinColumn(name="comboId",referencedColumnName="comboId") // 外键设置为套餐id
    private Combo combo;

    private LocalDateTime startDateTime;

    private LocalDateTime endDateTime;

    public ComboUsedRecord() {
    }

    public ComboUsedRecord(User user, Combo combo, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        this.user = user;
        this.combo = combo;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
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

    public Combo getCombo() {
        return combo;
    }

    public void setCombo(Combo combo) {
        this.combo = combo;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }
}
