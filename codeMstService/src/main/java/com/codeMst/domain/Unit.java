package com.codeMst.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Unit {

	@Id
    @GeneratedValue
    private Long id;

    /** 单位名称 */
	@Column(nullable = false)
    private String unitName;

    public Unit(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
}
