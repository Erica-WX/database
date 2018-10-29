package com.database.demo.dao;

import com.database.demo.entity.CallRecord;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CallRecordRepository extends CrudRepository<CallRecord, String> {

    Optional<CallRecord> findByPhoneNumber(String phoneNumber);

    Boolean existsByPhoneNumber(String phoneNumber);

}
