package com.database.demo.dao;

import com.database.demo.entity.MessageRecord;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface MessageRecordRepository extends CrudRepository<MessageRecord, String> {

    Optional<MessageRecord> findByPhoneNumber(String phoneNumber);

    Boolean existsByPhoneNumber(String phoneNumber);
}
