package org.spring.video;

import org.spring.video.properties.SystemSSLPropertiesConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@EnableDiscoveryClient
public class SpringvideoApplication {

	public static void main(String[] args) {
//		new SpringApplicationBuilder(Application.class).web(true).run(args);
		SpringApplication.run(SpringvideoApplication.class, args);
	}
}
