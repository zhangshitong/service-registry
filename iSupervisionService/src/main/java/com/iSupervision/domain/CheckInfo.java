package com.iSupervision.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class CheckInfo {

    @Id
    @GeneratedValue
    private Long id;

    /** 检查日期 */
    private String checkDate;
    
	/** 检查人员 
	 * 关联user.id */
	private Long userId;
	
	/** 检查单位 
	 * 关联unitMst.id */
	private Long unitId;
	
	/** 检查项目 */
	private String checkOption;
	
	/** 检查结果 */
	private String checkResultCode;
	
	/** 其他事项 */
	private String others;

    public CheckInfo(){}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCheckDate() {
		return checkDate;
	}

	public void setCheckDate(String checkDate) {
		this.checkDate = checkDate;
	}

	public String getCheckOption() {
		return checkOption;
	}

	public void setCheckOption(String checkOption) {
		this.checkOption = checkOption;
	}

	public String getCheckResultCode() {
		return checkResultCode;
	}

	public void setCheckResultCode(String checkResultCode) {
		this.checkResultCode = checkResultCode;
	}

	public String getOthers() {
		return others;
	}

	public void setOthers(String others) {
		this.others = others;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getUnitId() {
		return unitId;
	}

	public void setUnitId(Long unitId) {
		this.unitId = unitId;
	}


}
