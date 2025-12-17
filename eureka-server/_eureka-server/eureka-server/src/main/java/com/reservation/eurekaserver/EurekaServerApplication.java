package com.reservation.eurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaServerApplication.class, args);
		System.out.println("==================================");
		System.out.println("Eureka Server started successfully!");
		System.out.println("Access Dashboard: http://localhost:8761");
		System.out.println("==================================");
	}

}
