package com.codeMst.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class CodeMst {

    @Id
    @GeneratedValue
    private Long id;

    /** 项目类型 */
    @Column(nullable = false)
    private String codeType;
    
    /** 项目ID */
    @Column(nullable = false)
    private String codeId;
    
    /** 项目名称 */
    private String codeName;

    public CodeMst(){}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodeType() {
		return codeType;
	}

	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}

	public String getCodeId() {
		return codeId;
	}

	public void setCodeId(String codeId) {
		this.codeId = codeId;
	}

	public String getCodeName() {
		return codeName;
	}

	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}

}
