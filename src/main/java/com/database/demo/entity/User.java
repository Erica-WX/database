package com.database.demo.entity;


import com.sun.istack.internal.NotNull;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.Set;

/**
 * @author: 王轩
 * @description
 * @date: 2018/10/30
 */
@Entity
public class User {

    @Id
    @NotNull
    private String phoneNumber;

    @OneToMany(cascade={CascadeType.MERGE},fetch=FetchType.LAZY)
    @JoinColumn(name="phoneNumber")
    private Set<CallRecord> callRecordSet;

    @OneToMany(cascade={CascadeType.MERGE},fetch=FetchType.LAZY)
    @JoinColumn(name="phoneNumber")
    private Set<MessageRecord> messageRecordSet;

    @OneToMany(cascade={CascadeType.MERGE},fetch=FetchType.LAZY)
    @JoinColumn(name="phoneNumber")
    private Set<DataRecord> dataRecordSet;

    @OneToMany(cascade={CascadeType.MERGE},fetch=FetchType.LAZY)
    @JoinColumn(name="phoneNumber")
    private Set<ComboUsedRecord> comboUsedRecordSet;

    private String username;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<CallRecord> getCallRecordSet() {
        return callRecordSet;
    }

    public void setCallRecordSet(Set<CallRecord> callRecordSet) {
        this.callRecordSet = callRecordSet;
    }
}
