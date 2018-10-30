package com.database.demo.entity;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author: 王轩
 * @description
 * @date: 2018/10/30
 */

@Entity
public class CallRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //自增
    private Integer id;

    @ManyToOne(cascade={CascadeType.MERGE}, fetch=FetchType.EAGER)
    @JoinColumn(name="phoneNumber",referencedColumnName="phoneNumber") // 外键设置为phoneNumber
    private User user;

    private LocalDateTime startDateTime;

    private Integer minutes; // 通话时长

    private Double expense; //费用

    public CallRecord() {
    }

    public CallRecord(User user, LocalDateTime startDateTime, Integer minutes, Double expense) {
        this.user = user;
        this.startDateTime = startDateTime;
        this.minutes = minutes;
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

    public Integer getMinutes() {
        return minutes;
    }

    public void setMinutes(Integer minutes) {
        this.minutes = minutes;
    }

    public Double getExpense() {
        return expense;
    }

    public void setExpense(Double expense) {
        this.expense = expense;
    }
}
