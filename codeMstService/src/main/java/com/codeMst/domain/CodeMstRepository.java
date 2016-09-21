package com.codeMst.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


public interface CodeMstRepository extends JpaRepository<CodeMst, Long> {

	List<CodeMst> findByCodeType(String codeType);
	
	CodeMst findByCodeTypeAndCodeId(String codeType, String codeId);

}
