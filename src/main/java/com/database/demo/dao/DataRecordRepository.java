package com.database.demo.dao;

import com.database.demo.entity.DataRecord;
import com.database.demo.entity.DataType;
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
public interface DataRecordRepository extends CrudRepository<DataRecord, String> {

    List<DataRecord> findByUserAndDataType(User user, DataType type);

    Boolean existsByUser(User user);

    List<DataRecord> findByUserAndDataTypeAndStartDateTimeBetween(User user, DataType type, LocalDateTime firstDay, LocalDateTime LastDay);

    @Query(value = "select sum(expense) from data_record where phone_number = ?1 and start_date_time >= ?2 and start_date_time <= ?3 and data_type = ?4", nativeQuery = true)
    Double getSumOfDataExpense(String phoneNumber, LocalDateTime startDateTime, LocalDateTime endDateTime, String type);
}
