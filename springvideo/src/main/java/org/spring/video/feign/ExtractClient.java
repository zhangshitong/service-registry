package org.spring.video.feign;

import org.spring.video.feign.configuration.FeignClientConfiguration;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "http://extract-service/srv", configuration = FeignClientConfiguration.class)
public interface ExtractClient {

	@RequestMapping(method = RequestMethod.GET, value = "/console/extract")
    String extract(@RequestParam(value = "name") String name);
}
