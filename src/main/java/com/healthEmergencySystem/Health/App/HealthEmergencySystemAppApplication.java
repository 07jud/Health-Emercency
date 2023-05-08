package com.healthEmergencySystem.Health.App;

import com.healthEmergencySystem.Health.App.Services.FilesStorageService;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

@SpringBootApplication
public class HealthEmergencySystemAppApplication implements CommandLineRunner {
	@Resource
	FilesStorageService storageService;

	public static void main(String[] args) {
		SpringApplication.run(HealthEmergencySystemAppApplication.class, args);

	}

	@Override
	public void run(String... arg) throws Exception {
		storageService.init();
	}
}
