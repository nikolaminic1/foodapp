package com.example.foodapp.business.repo;

import com.example.foodapp.business.model.TimeOpenedWeek;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface BusinessWorkingTimeRepo extends JpaRepository<TimeOpenedWeek, Long> {
    Optional<TimeOpenedWeek> findTimeOpenedWeekById(Long id);
}
