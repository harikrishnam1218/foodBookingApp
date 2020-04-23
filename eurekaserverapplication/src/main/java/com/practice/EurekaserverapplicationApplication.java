package com.practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
@EnableEurekaServer
@SpringBootApplication
public class EurekaserverapplicationApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaserverapplicationApplication.class, args);
	}

}
