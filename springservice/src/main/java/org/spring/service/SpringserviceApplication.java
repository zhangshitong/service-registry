package org.spring.service;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SpringserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringserviceApplication.class, args);
	}
}
