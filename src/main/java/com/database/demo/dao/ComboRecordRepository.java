package com.database.demo.dao;

import com.database.demo.entity.ComboUsedRecord;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ComboRecordRepository extends CrudRepository<ComboUsedRecord, String> {

    Optional<ComboUsedRecord> findByPhoneNumber(String phoneNumber);

    Boolean existsByPhoneNumber(String phoneNumber);

    Optional<ComboUsedRecord> findByComboId(String comboId);

    Boolean existsByComboId(String comboId);
}
