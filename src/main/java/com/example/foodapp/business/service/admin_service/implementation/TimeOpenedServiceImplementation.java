package com.example.foodapp.business.service.admin_service.implementation;

import com.example.foodapp.business.model.Business;
import com.example.foodapp.business.model.TimeOpenedDay;
import com.example.foodapp.business.model.TimeOpenedWeek;
import com.example.foodapp.business.repo.BusinessRepo;
import com.example.foodapp.business.repo.TimeOpenedDayRepo;
import com.example.foodapp.business.repo.TimeOpenedWeekRepo;
import com.example.foodapp.business.service.admin_service.TimeOpenedService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.hibernate.boot.model.source.internal.hbm.AttributesHelper;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
@Log4j2
public class TimeOpenedServiceImplementation implements TimeOpenedService {
    private final TimeOpenedWeekRepo timeOpenedWeekRepo;
    private final TimeOpenedDayRepo timeOpenedDayRepo;
    private final BusinessRepo businessRepo;
    @Override
    public TimeOpenedWeek save(Business business) throws Exception {
        TimeOpenedWeek openedWeek = new TimeOpenedWeek();

        TimeOpenedDay monday = new TimeOpenedDay("08:00", "20:00", false);
        TimeOpenedDay tuesday = new TimeOpenedDay("08:00", "20:00", false);
        TimeOpenedDay wednesday = new TimeOpenedDay("08:00", "20:00", false);
        TimeOpenedDay thursday = new TimeOpenedDay("08:00", "20:00", false);
        TimeOpenedDay friday = new TimeOpenedDay("08:00", "20:00", false);
        TimeOpenedDay saturday = new TimeOpenedDay("08:00", "20:00", false);
        TimeOpenedDay sunday = new TimeOpenedDay("08:00", "20:00", false);
        var m = List.of(monday, tuesday, thursday, wednesday, friday, saturday, sunday);
        timeOpenedDayRepo.saveAll(m);

        openedWeek.setTimeOpenedDayMonday(monday);
        openedWeek.setTimeOpenedDayTuesday(tuesday);
        openedWeek.setTimeOpenedDayWednesday(wednesday);
        openedWeek.setTimeOpenedDayThursday(thursday);
        openedWeek.setTimeOpenedDayFriday(friday);
        openedWeek.setTimeOpenedDaySaturday(saturday);
        openedWeek.setTimeOpenedDaySunday(sunday);
        timeOpenedWeekRepo.save(openedWeek);
        business.setTimeOpened(openedWeek);
        businessRepo.save(business);
        return openedWeek;
    }
}
