package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class MyServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyServiceApplication.class, args);
	}
	
	@RequestMapping(value = "/findRestUrl", method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public String findRestUrl() {
		
		return "var serviceUrl = 'http://localhost:8089'";
    }
}
