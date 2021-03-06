package com.database.demo.dao;

import com.database.demo.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * @author: 王轩
 * @description
 * @date: 2018/10/30
 */
public interface UserRepository extends CrudRepository<User, String> {
    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByPhoneNumber(String phoneNumber);

    Optional<User> findByPhoneNumber(String phoneNumber);
}
