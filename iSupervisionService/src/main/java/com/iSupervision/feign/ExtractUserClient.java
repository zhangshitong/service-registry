package com.iSupervision.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.iSupervision.domain.UserMap;


@FeignClient(value = "http://oauth-service/uaa")
public interface ExtractUserClient {

	@RequestMapping("/user/fetchUser")
	UserMap user(@RequestParam(value = "name", required = true) String name);
	
}
