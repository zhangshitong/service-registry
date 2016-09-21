package com.iSupervisionUi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@SpringBootApplication
@RestController
@EnableDiscoveryClient
@EnableConfigurationProperties(WebApplicationServiceUrlConfiguration.class)
public class MyServiceApplication {


	private Logger logger = LoggerFactory.getLogger(MyServiceApplication.class);

	@Autowired
	private LoadBalancerClient loadBalancer;

	@Autowired
	private WebApplicationServiceUrlConfiguration webApplicationServiceUrlConfiguration;


	public static void main(String[] args) {
		SpringApplication.run(MyServiceApplication.class, args);
	}
	
	@RequestMapping(value = "/findRestUrl", method = {RequestMethod.GET})
    @ResponseBody
    public String findRestUrl() {

		Map<String, String> extractUrlMap = extractUrls();
		Iterator<Map.Entry<String, String> > it = extractUrlMap.entrySet().iterator();
		StringBuilder sb = new StringBuilder();
		boolean first = true;
		while (it.hasNext()) {
			Map.Entry<String, String> etry = it.next();
			String applicationName = etry.getKey().replaceAll("-", "_");
			applicationName += "_ServiceUrl";
			if(first){
				sb.append("\n").append("var serviceUrl = '"+etry.getValue()+"';");
				first = false;
			}
			sb.append("\n").append("var "+applicationName + " = '"+etry.getValue()+"';");
		}
		return sb.toString();
    }
	
	
	@Controller
	public static class indexControll{
		
		@RequestMapping(value = "/", method = {RequestMethod.GET})
	    public String index() {
			return "redirect:home.html";
	    }
	}
	



	public Map<String, String> extractUrls() {
		Map<String, String> serviceUrlMap = webApplicationServiceUrlConfiguration.getServiceUrl();
		logger.info("serviceUrlMap=" + serviceUrlMap);
		Map<String, String> extractUrlMap = new HashMap<>();
		if(serviceUrlMap != null && !serviceUrlMap.isEmpty()) {
			Iterator<Map.Entry<String, String> > it = serviceUrlMap.entrySet().iterator();
			while (it.hasNext()){
				Map.Entry<String, String> etry = it.next();
				URI uri = null;
				try {
					uri = new URI(etry.getValue().trim());
				} catch (URISyntaxException e) {
					e.printStackTrace();
				}
				if (uri != null) {
					ServiceInstance serviceInstance = loadBalancer.choose(etry.getKey());
					if(serviceInstance != null) {
						String serviceUrl = loadBalancer.reconstructURI(serviceInstance, uri).toString();
						extractUrlMap.put(etry.getKey(), serviceUrl);
						logger.info("" + etry.getKey() + ".serviceUrl=" + serviceUrl);
					}
				}
			}
		}
		//return
		return extractUrlMap;
	}
}
