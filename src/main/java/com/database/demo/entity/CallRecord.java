package com.database.demo.entity;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class CallRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //自增
    private Integer id;

    @ManyToOne(cascade={CascadeType.ALL}, fetch=FetchType.EAGER)
    @JoinColumn(name="phoneNumber",referencedColumnName="phoneNumber") // 外键设置为phoneNumber
    private User user;

    private LocalDate startDateTime;

    private Integer minutes; // 通话时长

    private Double expense; //费用

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
