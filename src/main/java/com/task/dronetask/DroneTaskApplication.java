package com.task.dronetask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication

public class DroneTaskApplication {
	//create a bean for rest template to make api cals

	public static void main(String[] args) {
		SpringApplication.run(DroneTaskApplication.class, args);
	}
	@Bean
	//password encoding bean
	public BCryptPasswordEncoder bCryptPasswordEncoder(){
		return new BCryptPasswordEncoder();
	}
}
