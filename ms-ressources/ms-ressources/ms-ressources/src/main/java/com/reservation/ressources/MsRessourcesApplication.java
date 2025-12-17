package com.reservation.ressources;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
@EnableDiscoveryClient
public class MsRessourcesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsRessourcesApplication.class, args);
		System.out.println("==================================");
		System.out.println("MS Ressources started successfully!");
		System.out.println("Port: 8081");
		System.out.println("==================================");
	}

}
