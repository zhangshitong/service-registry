package com.iSupervision.feign;

import java.util.List;

import com.iSupervision.feign.configuration.FeignClientConfiguration;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.iSupervision.domain.CodeMstMap;
import com.iSupervision.domain.UnitMap;

@FeignClient(value = "http://code-service", configuration = FeignClientConfiguration.class)
public interface ExtractClient {

	@RequestMapping(method = {RequestMethod.GET}, value = "/code/findUnitById")
	UnitMap findUnitById(@RequestParam("id") String id);
	
	@RequestMapping(method = {RequestMethod.GET}, value = "/code/findByCodeTypeAndCodeId")
	CodeMstMap findByCodeTypeAndCodeId(@RequestParam("codeType") String codeType, @RequestParam("codeId") String codeId);
	
	@RequestMapping(value = "/code/findAllUnit", method = {RequestMethod.GET})
    List<UnitMap> findAllUnit();
	
	@RequestMapping(value = "/code/findByCodeType", method = {RequestMethod.GET})
    List<CodeMstMap> findByCodeType(@RequestParam("codeType") String codeType);
	
}
