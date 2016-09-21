package org.spring.video;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@EnableJpaRepositories
@EnableFeignClients
@EnableDiscoveryClient
@EnableConfigurationProperties(WebApplicationServiceUrlConfiguration.class)
public class SpringvideoApplication {

	public static void main(String[] args) {
//		new SpringApplicationBuilder(Application.class).web(true).run(args);
		SpringApplication.run(SpringvideoApplication.class, args);
	}
}
