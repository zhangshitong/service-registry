package com.iSupervision.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.iSupervision.domain.UserMap;
import com.iSupervision.feign.configuration.FeignClientConfiguration;

@FeignClient(value = "http://oauth.iop.com/uaa", configuration = FeignClientConfiguration.class)
public interface ExtractUserClient {

	@RequestMapping("/oauth/user/fetchUser")
	UserMap user(@RequestParam(required = true) String name);
	
}
