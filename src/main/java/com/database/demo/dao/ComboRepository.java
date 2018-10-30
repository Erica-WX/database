package com.database.demo.dao;

import com.database.demo.entity.Combo;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * @author: 王轩
 * @description
 * @date: 2018/10/30
 */
public interface ComboRepository extends CrudRepository<Combo, String> {
    Optional<Combo> findByComboId(Integer comboId);

    Boolean existsByComboId(Integer comboId);
}
