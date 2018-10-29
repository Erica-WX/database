package com.database.demo.dao;

import com.database.demo.entity.Combo;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ComboRepository extends CrudRepository<Combo, String> {
    Optional<Combo> findByComboId(String comboId);

    Boolean existsByComboId(String comboId);
}
