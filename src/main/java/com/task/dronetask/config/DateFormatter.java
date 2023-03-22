package com.task.dronetask.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.format.DateTimeFormatter;

@Configuration
public class DateFormatter {
    @Bean
    public DateTimeFormatter getDateFormatter(){
        return  DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss.SSS");
    }
}
