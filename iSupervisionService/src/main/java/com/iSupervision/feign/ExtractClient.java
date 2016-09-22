package com.iSupervision.feign;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.iSupervision.domain.CodeMstMap;
import com.iSupervision.domain.UnitMap;
import com.iSupervision.feign.configuration.FeignClientConfiguration;

@FeignClient(value = "http://code-service/srv", configuration = FeignClientConfiguration.class)
public interface ExtractClient {

	@RequestMapping(method = RequestMethod.GET, value = "/findUnitById")
	UnitMap findUnitById(@RequestParam String id);
	
	@RequestMapping(method = RequestMethod.GET, value = "/findByCodeTypeAndCodeId")
	CodeMstMap findByCodeTypeAndCodeId(@RequestParam String codeType, @RequestParam String codeId);
	
	@RequestMapping(value = "/findAllUnit", method = {RequestMethod.GET,RequestMethod.POST})
    List<UnitMap> findAllUnit();
	
	@RequestMapping(value = "/findByCodeType", method = {RequestMethod.GET,RequestMethod.POST})
    List<CodeMstMap> findByCodeType(@RequestParam String codeType);
	
}
