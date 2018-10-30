package com.database.demo.dao;

import com.database.demo.entity.CallRecord;
import com.database.demo.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * @author: 王轩
 * @description
 * @date: 2018/10/30
 */
public interface CallRecordRepository extends CrudRepository<CallRecord, String> {

    List<CallRecord> findAllByUser(User user);

    Boolean existsByUser(User user);

    List<CallRecord> findByUserAndStartDateTimeBetween(User user, LocalDateTime firstDay, LocalDateTime lastDay);

    @Query(value = "select sum(expense) from call_record where phone_number = ?1 and start_date_time >= ?2 and start_date_time <= ?3", nativeQuery = true)
    Double getSumOfExpense(String phoneNumber, LocalDateTime startDateTime, LocalDateTime endDateTime);

}
