package com.iSupervision.domain;

public class CheckInfoExt {
    
	/** 检查清单 */
	private CheckInfo checkInfo;
	
	/** 检查人员姓名 */
	private String userName;
	
	/** 检查单位名称*/
	private String unitName;
	
	/** 检查结果名称*/
	private String checkResultName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getCheckResultName() {
		return checkResultName;
	}

	public void setCheckResultName(String checkResultName) {
		this.checkResultName = checkResultName;
	}

	public CheckInfo getCheckInfo() {
		return checkInfo;
	}

	public void setCheckInfo(CheckInfo checkInfo) {
		this.checkInfo = checkInfo;
	}

}
