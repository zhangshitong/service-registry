package com.iSupervision;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.iSupervision.domain.CheckInfo;
import com.iSupervision.domain.CheckInfoExt;
import com.iSupervision.domain.CheckInfoRepository;
import com.iSupervision.domain.CodeMstMap;
import com.iSupervision.domain.UnitMap;
import com.iSupervision.domain.UserMap;
import com.iSupervision.feign.ExtractClient;


@SpringBootApplication
@RestController
@EnableDiscoveryClient
@EnableFeignClients
public class MyServiceApplication {

	@Autowired
	private CheckInfoRepository checkInfoRepo;
	
	@Autowired
	private ExtractClient extractClient;
	
//	private final static String url = "http://code-service";
	
	public static void main(String[] args) {
		SpringApplication.run(MyServiceApplication.class, args);
	}
	
	@RequestMapping(value = "/findAllCheckInfo", method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public List<CheckInfoExt> findAllCheckInfo() {
		
		// TODO 测试数据
//		checkInfoRepo.deleteAll();
//		if (checkInfoRepo.count() == 0) {
//			CheckInfo checkInfo1 = new CheckInfo();
//			checkInfo1.setCheckDate("2016/09/21");
//			checkInfo1.setCheckOption("安全生产");
//			checkInfo1.setCheckResultCode("1");
//			checkInfo1.setOthers("无特殊情况");
//			checkInfo1.setUnitId(Long.parseLong("1"));
//			checkInfo1.setUserId(Long.parseLong("1"));
//			
//			checkInfo1 = checkInfoRepo.save(checkInfo1);
//		}
		
		List<CheckInfoExt> checkInfoExts = new ArrayList<CheckInfoExt>();
		
		List<CheckInfo> checkInfos = checkInfoRepo.findAll();
		
//		RestTemplate restTemplate = new RestTemplate();
//		
//		MultiValueMap<String, Object> param = null;
		CodeMstMap codeMstMap = null;
		UnitMap unitMap = null;
		
		for (CheckInfo checkInfo : checkInfos) {
			CheckInfoExt checkInfoExt = new CheckInfoExt();
			
			checkInfoExt.setCheckInfo(checkInfo);
			
			// 调用外部Service获取单位信息
//			param = new LinkedMultiValueMap<>();
//			param.add("id", checkInfo.getUnitId().toString());
//			
//			unitMap = restTemplate.postForObject(url + "/findUnitById", param, UnitMap.class);
			
			unitMap = extractClient.findUnitById(checkInfo.getUnitId().toString());
			checkInfoExt.setUnitName(unitMap.getUnitName());
			
			// 调用外部Service获取检查结果信息
//			param.clear();
//			param.add("codeType", "1");
//			param.add("codeId", checkInfo.getCheckResultCode());
//			codeMstMap = restTemplate.postForObject(url + "/findByCodeTypeAndCodeId", param, CodeMstMap.class);
			
			codeMstMap = extractClient.findByCodeTypeAndCodeId("1",checkInfo.getCheckResultCode());
			checkInfoExt.setCheckResultName(codeMstMap.getCodeName());
			
			// TODO 调用外部Service获取用户信息
			checkInfoExt.setUserName("Admin");

			checkInfoExts.add(checkInfoExt);
		}
		
		return checkInfoExts;
    }
	
	@RequestMapping(value = "/getCheckInfo", method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public CheckInfoExt getCheckInfo(@RequestParam String id) {
		
//		RestTemplate restTemplate = new RestTemplate();
		
//		MultiValueMap<String, Object> param = null;
		CodeMstMap codeMstMap = null;
		UnitMap unitMap = null;
		
		CheckInfoExt checkInfoExt = new CheckInfoExt();
		CheckInfo checkInfo = checkInfoRepo.findById(Long.parseLong(id));
		checkInfoExt.setCheckInfo(checkInfo);
		
		// 调用外部Service获取单位信息
//		param = new LinkedMultiValueMap<>();
//		param.add("id", checkInfo.getUnitId().toString());
//		
//		unitMap = restTemplate.postForObject(url + "/findUnitById", param, UnitMap.class);
		
		unitMap = extractClient.findUnitById(checkInfo.getUnitId().toString());
		checkInfoExt.setUnitName(unitMap.getUnitName());
		
		// 调用外部Service获取检查结果信息
//		param.clear();
//		param.add("codeType", "1");
//		param.add("codeId", checkInfo.getCheckResultCode());
//		codeMstMap = restTemplate.postForObject(url + "/findByCodeTypeAndCodeId", param, CodeMstMap.class);
		
		codeMstMap = extractClient.findByCodeTypeAndCodeId("1",checkInfo.getCheckResultCode());
		checkInfoExt.setCheckResultName(codeMstMap.getCodeName());
		
		// TODO 调用外部Service获取用户信息
		checkInfoExt.setUserName("Admin");
		
		return checkInfoExt;
    }
	
	@RequestMapping(value = "/addCheckInfo", method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public String addCheckInfo(HttpServletRequest request) {
		
		CheckInfo checkInfo = new CheckInfo();
		checkInfo.setCheckDate(request.getParameter("checkDate"));
		checkInfo.setCheckOption(request.getParameter("checkOption"));
		checkInfo.setCheckResultCode(request.getParameter("checkResultCode"));
		checkInfo.setOthers(request.getParameter("others"));
		checkInfo.setUnitId(Long.parseLong(request.getParameter("unitId")));
		checkInfo.setUserId(Long.parseLong(request.getParameter("userId")));
		
		checkInfo = checkInfoRepo.save(checkInfo);
		
		return checkInfo.getId().toString();
    }
	
	@RequestMapping(value = "/delCheckInfo", method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public String delCheckInfo(@RequestParam String id) {
		
		checkInfoRepo.delete(Long.parseLong(id));
		
		return "success";
    }
	
	@RequestMapping(value = "/getUserInfo", method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public UserMap getUserInfo() {
		
		// TODO 调用外部Service获取用户信息
		UserMap userMap = new UserMap();
		userMap.setUserId("1");
		userMap.setUserName("Admin");
		
		return userMap;
    }

	@RequestMapping(value = "/findAllUnit", method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public List<UnitMap> findAllUnit() {
		
//		RestTemplate restTemplate = new RestTemplate();
//		
//		MultiValueMap<String, Object> param = null;
		List<UnitMap> unitMaps = null;
		
		// 调用外部Service获取单位信息
//		param = new LinkedMultiValueMap<>();
		
//		unitMaps = restTemplate.postForObject(url + "/findAllUnit", param, List.class);
		
		unitMaps = extractClient.findAllUnit();
		
		return unitMaps;
    }
	
	@RequestMapping(value = "/findByCodeType", method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public List<CodeMstMap> findByCodeType(@RequestParam String codeType) {
		
//		RestTemplate restTemplate = new RestTemplate();
//		
//		MultiValueMap<String, Object> param = null;
		List<CodeMstMap> codeMstMaps = null;
		
		// 调用外部Service获取单位信息
//		param = new LinkedMultiValueMap<>();
//		param.add("codeType", "1");
//		
//		codeMstMaps = restTemplate.postForObject(url + "/findByCodeType", param, List.class);
		
		codeMstMaps = extractClient.findByCodeType("1");
		
		return codeMstMaps;
    }

	@RequestMapping(value = "/index", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public String index() {
		return "welcome";
	}


}
