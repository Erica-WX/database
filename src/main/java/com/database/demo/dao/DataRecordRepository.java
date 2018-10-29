package com.database.demo.dao;

import com.database.demo.entity.CallRecord;
import com.database.demo.entity.DataRecord;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DataRecordRepository extends CrudRepository<DataRecord, String> {

    Optional<DataRecord> findByPhoneNumber(String phoneNumber);

    Boolean existsByPhoneNumber(String phoneNumber);
}
