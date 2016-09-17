package org.spring.video;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@EnableFeignClients
//@EnableDiscoveryClient
public class SpringvideoApplication {

	public static void main(String[] args) {
//		new SpringApplicationBuilder(Application.class).web(true).run(args);
		SpringApplication.run(SpringvideoApplication.class, args);
	}
}
