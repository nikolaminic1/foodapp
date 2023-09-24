package com.example.foodapp.business.repo;

import com.example.foodapp.business.model.TimeOpenedDay;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TimeOpenedDayRepo extends JpaRepository<TimeOpenedDay, Long> {
    Optional<TimeOpenedDay> findById(Long id);
}
