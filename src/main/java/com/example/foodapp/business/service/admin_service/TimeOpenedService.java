package com.example.foodapp.business.service.admin_service;

import com.example.foodapp.business.model.Business;
import com.example.foodapp.business.model.TimeOpenedWeek;

import java.util.Optional;

public interface TimeOpenedService {
    TimeOpenedWeek save(Business business) throws Exception;
}
