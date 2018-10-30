package com.database.demo.dao;

import com.database.demo.entity.MessageRecord;
import com.database.demo.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


public interface MessageRecordRepository extends CrudRepository<MessageRecord, String> {

    Optional<MessageRecord> findByUser(User user);

    Boolean existsByUser(User user);

    List<MessageRecord> findByUserAndStartDateTimeBetween(User user, LocalDateTime firstDay,LocalDateTime lastDay);

    @Query(value = "select sum(expense) from message_record where phone_number = ?1 and start_date_time >= ?2 and start_date_time <= ?3", nativeQuery = true)
    Double getSumOfExpense(String phoneNumber, LocalDateTime startDateTime, LocalDateTime endDateTime);
}
