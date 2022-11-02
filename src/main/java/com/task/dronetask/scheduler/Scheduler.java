package com.task.dronetask.scheduler;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import com.task.dronetask.converter.DroneConverterImpl;
import com.task.dronetask.dto.DroneDto;
import com.task.dronetask.entity.BatteryHistory;
import com.task.dronetask.entity.Drones;
import com.task.dronetask.repository.BatteryRepository;
import com.task.dronetask.service.DroneServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
//configuration allows this class create beans
@Configuration
//Enable scheduling allows us perform cron jobs to enable you perform periodic tasks in the scheduler class
@EnableScheduling
public class Scheduler {
    @Autowired
    DroneServiceImpl droneService;
    BatteryHistory batteryHistory;
    @Autowired
    DroneConverterImpl droneConverter;
    @Autowired
    BatteryRepository batteryRepository;
    DateTimeFormatter timeStampPattern  = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss");
    @Scheduled(fixedDelayString = "PT10S", initialDelay = 3000)
    public void fixedDelaySch() {
        for(DroneDto drone:droneService.getAllDrones()){
            BatteryHistory battery = new BatteryHistory();
            battery.setBatteryPercent(drone.getBatteryCapacity());
            battery.setTime(timeStampPattern.format(LocalDateTime.now()));
            battery.setDrone(droneConverter.droneDtoToEntity(drone));
            batteryRepository.save(battery);
        }
    }

}

