package com.iSupervision.feign;

import com.iSupervision.feign.configuration.FeignClientConfiguration;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.iSupervision.domain.UserMap;


@FeignClient(value = "http://oauth-server/uaa", configuration = FeignClientConfiguration.class)
public interface ExtractUserClient {

	@RequestMapping("/oauth/user/fetchUser")
	UserMap user(@RequestParam(value = "name", required = true) String name);
	
}
