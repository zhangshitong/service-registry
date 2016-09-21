package com.codeMst;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.codeMst.domain.CodeMst;
import com.codeMst.domain.CodeMstRepository;
import com.codeMst.domain.Unit;
import com.codeMst.domain.UnitRepository;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
@RequestMapping(value = "/code")
public class MyServiceApplication {

	@Autowired
	private CodeMstRepository codeMstRepo;
	
	@Autowired
	private UnitRepository unitRepo;
	
	public static void main(String[] args) {
		SpringApplication.run(MyServiceApplication.class, args);
	}
	
	@RequestMapping(value = "/findAllCode", method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public List<CodeMst> findAllCode() {
		
		insertBaseCodeData();
		
		List<CodeMst> codeMsts = codeMstRepo.findAll();
		
		return codeMsts;
    }
	
	@RequestMapping(value = "/findByCodeType", method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public List<CodeMst> findByCodeType(@RequestParam String codeType) {
		
		insertBaseCodeData();
		
		List<CodeMst> codeMsts = codeMstRepo.findByCodeType(codeType);
		
		return codeMsts;
    }
	
	@RequestMapping(value = "/findByCodeTypeAndCodeId", method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public CodeMst findByCodeTypeAndCodeId(@RequestParam String codeType
    		, @RequestParam String codeId) {
		
		insertBaseCodeData();
		
		CodeMst codeMst = codeMstRepo.findByCodeTypeAndCodeId(codeType, codeId);
		
		return codeMst;
    }
	
	@RequestMapping(value = "/findAllUnit", method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public List<Unit> findAllUnit() {
		
		insertBaseUnitData();
		
		List<Unit> units = unitRepo.findAll();
		
		return units;
    }
	
	@RequestMapping(value = "/findUnitById", method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Unit findUnitById(@RequestParam String id) {
		
		insertBaseUnitData();
		
		Unit unit = unitRepo.findById(Long.parseLong(id));
		
		return unit;
    }
	
	private void insertBaseUnitData() {
		if (unitRepo.count() == 0) {
			List<Unit> units = new ArrayList<Unit>();
			
			Unit unit = new Unit();
			unit.setUnitName("国家铁路局/督查办");
			units.add(unit);
			
			unit = new Unit();
			unit.setUnitName("各业务司/督察组");
			units.add(unit);
			
			unitRepo.save(units);
		}
	}
	
	private void insertBaseCodeData() {
		if (codeMstRepo.count() == 0) {
			List<CodeMst> codeMsts = new ArrayList<CodeMst>();
			
			CodeMst codeMst = new CodeMst();
			codeMst.setCodeType("1");
			codeMst.setCodeId("1");
			codeMst.setCodeName("完全达标");
			codeMsts.add(codeMst);
			
			codeMst = new CodeMst();
			codeMst.setCodeType("1");
			codeMst.setCodeId("2");
			codeMst.setCodeName("基本达标");
			codeMsts.add(codeMst);
			
			codeMst = new CodeMst();
			codeMst.setCodeType("1");
			codeMst.setCodeId("3");
			codeMst.setCodeName("不达标，要求整改");
			codeMsts.add(codeMst);
			
			codeMstRepo.save(codeMsts);
		}
	}
}
