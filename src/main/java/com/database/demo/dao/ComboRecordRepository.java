package com.database.demo.dao;

import com.database.demo.entity.Combo;
import com.database.demo.entity.ComboUsedRecord;
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
public interface ComboRecordRepository extends CrudRepository<ComboUsedRecord, String> {

    List<ComboUsedRecord> findAllByUser(User user);

    List<ComboUsedRecord> findAllByUserAndCombo(User user, Combo combo);

    @Query(value = "select sum(t2.month_expense) from combo_used_record t1 , combo t2 where t1.combo_id = t2.combo_id and t1.phone_number = ?1 and t1.start_date_time <= ?2 and t1.end_date_time >= ?2", nativeQuery = true)
    Double getSumOfExpense(String phoneNumber, LocalDateTime nowDateTime);
}
