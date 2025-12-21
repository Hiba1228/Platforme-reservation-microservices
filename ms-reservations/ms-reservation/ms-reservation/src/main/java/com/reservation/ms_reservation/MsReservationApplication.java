package com.reservation.ms_reservation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class MsReservationApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsReservationApplication.class, args);
		System.out.println("==================================");
		System.out.println("MS Réservations started successfully!");
		System.out.println("Port: 8082");
		System.out.println("==================================");
	}
}