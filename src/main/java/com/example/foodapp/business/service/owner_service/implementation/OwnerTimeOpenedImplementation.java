package com.example.foodapp.business.service.owner_service.implementation;

import com.example.foodapp.business.model.TimeOpenedDay;
import com.example.foodapp.business.repo.TimeOpenedDayRepo;
import com.example.foodapp.business.service.owner_service.OwnerTimeOpenedDayService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static com.example.foodapp.api_resources.ParsedDate.parseDate;
import static java.time.format.DateTimeFormatter.ISO_ZONED_DATE_TIME;

@RequiredArgsConstructor
@Service
@Transactional
@Log4j2
public class OwnerTimeOpenedImplementation implements OwnerTimeOpenedDayService {
    private final TimeOpenedDayRepo timeOpenedDayRepo;

    @Override
    public Boolean create(Map<String, List> payload) {

        List monday = payload.get("mondayData");
        List tuesday = payload.get("tuesdayData");
        List wednesday = payload.get("wednesdayData");
        List thursday = payload.get("thursdayData");
        List friday = payload.get("fridayData");
        List saturday = payload.get("saturdayData");
        List sunday = payload.get("sundayData");

        List mondayNonStop = payload.get("mondayNonStop");
        List tuesdayNonStop = payload.get("tuesdayNonStop");
        List wednesdayNonStop = payload.get("wednesdayNonStop");
        List thursdayNonStop = payload.get("thursdayNonStop");
        List fridayNonStop = payload.get("fridayNonStop");
        List saturdayNonStop = payload.get("saturdayNonStop");
        List sundayNonStop = payload.get("sundayNonStop");

        TimeOpenedDay timeOpenedDayMonday = new TimeOpenedDay();
        TimeOpenedDay timeOpenedDayTuesday = new TimeOpenedDay();
        TimeOpenedDay timeOpenedDayWednesday = new TimeOpenedDay();
        TimeOpenedDay timeOpenedDayThursday = new TimeOpenedDay();
        TimeOpenedDay timeOpenedDayFriday = new TimeOpenedDay();
        TimeOpenedDay timeOpenedDaySaturday = new TimeOpenedDay();
        TimeOpenedDay timeOpenedDaySunday = new TimeOpenedDay();

        try{
            if(mondayNonStop.get(0) instanceof Boolean){
                if((Boolean) mondayNonStop.get(0)){
                    timeOpenedDayMonday.setIsNonStop(true);
                } else {
                    List mondayList = parseDate(monday);
                    timeOpenedDayMonday.setTimeOpen((String) mondayList.get(0));
                    timeOpenedDayMonday.setTimeClose((String) mondayList.get(1));
                }
            } else {
                return false;
            }

            if(tuesdayNonStop.get(0) instanceof Boolean){
                if((Boolean) tuesdayNonStop.get(0)){
                    timeOpenedDayTuesday.setIsNonStop(true);
                } else {
                    List tuesdayList = parseDate(tuesday);
                    timeOpenedDayTuesday.setTimeOpen((String) tuesdayList.get(0));
                    timeOpenedDayTuesday.setTimeClose((String) tuesdayList.get(1));
                }
            }  else {
                return false;
            }


            if(wednesdayNonStop.get(0) instanceof Boolean){
                if((Boolean) wednesdayNonStop.get(0)){
                    timeOpenedDayWednesday.setIsNonStop(true);
                } else {
                    List wednesdayList = parseDate(wednesday);
                    timeOpenedDayWednesday.setTimeOpen((String) wednesdayList.get(0));
                    timeOpenedDayWednesday.setTimeClose((String) wednesdayList.get(1));
                }
            } else {
                return false;
            }


            if(thursdayNonStop.get(0) instanceof Boolean){
                if((Boolean) thursdayNonStop.get(0)){
                    timeOpenedDayThursday.setIsNonStop(true);
                } else {
                    List thursdayList = parseDate(thursday);
                    timeOpenedDayThursday.setTimeOpen((String) thursdayList.get(0));
                    timeOpenedDayThursday.setTimeClose((String) thursdayList.get(1));
                }
            } else {
                return false;
            }


            if(fridayNonStop.get(0) instanceof Boolean){
                if((Boolean) fridayNonStop.get(0)){
                    timeOpenedDayFriday.setIsNonStop(true);
                } else {
                    List fridayList = parseDate(friday);
                    timeOpenedDayFriday.setTimeOpen((String) fridayList.get(0));
                    timeOpenedDayFriday.setTimeClose((String) fridayList.get(1));
                }
            } else {
                return false;
            }


            if(saturdayNonStop.get(0) instanceof Boolean){
                if((Boolean) saturdayNonStop.get(0)){
                    timeOpenedDaySaturday.setIsNonStop(true);
                } else {
                    List saturdayList = parseDate(saturday);
                    timeOpenedDaySaturday.setTimeOpen((String) saturdayList.get(0));
                    timeOpenedDaySaturday.setTimeClose((String) saturdayList.get(1));
                }
            } else {
                return false;
            }


            if(sundayNonStop.get(0) instanceof Boolean){
                if((Boolean) sundayNonStop.get(0)){
                    timeOpenedDaySunday.setIsNonStop(true);
                } else {
                    List sundayList = parseDate(sunday);
                    timeOpenedDaySunday.setTimeOpen((String) sundayList.get(0));
                    timeOpenedDaySunday.setTimeClose((String) sundayList.get(1));
                }
            } else {
                return false;
            }


            timeOpenedDayRepo.save(timeOpenedDayMonday);
            timeOpenedDayRepo.save(timeOpenedDayTuesday);
            timeOpenedDayRepo.save(timeOpenedDayWednesday);
            timeOpenedDayRepo.save(timeOpenedDayThursday);
            timeOpenedDayRepo.save(timeOpenedDayFriday);
            timeOpenedDayRepo.save(timeOpenedDaySaturday);
            timeOpenedDayRepo.save(timeOpenedDaySunday);

        } catch (Exception e){
            System.out.println(e);

            return false;
        }

        return true;
    }

    @Override
    public TimeOpenedDay get(Long id) {
        return null;
    }

    @Override
    public TimeOpenedDay update(Long id) {
        return null;
    }

    @Override
    public Boolean delete(Long id) {
        return null;
    }
}
