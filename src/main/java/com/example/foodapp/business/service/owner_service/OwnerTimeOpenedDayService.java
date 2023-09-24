package com.example.foodapp.business.service.owner_service;


import com.example.foodapp.business.model.TimeOpenedDay;

import java.text.ParseException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface OwnerTimeOpenedDayService {
    Boolean create(Map<String, List> timeOpened);
    TimeOpenedDay get(Long id);
    TimeOpenedDay update(Long id);
    Boolean delete(Long id);
    }
