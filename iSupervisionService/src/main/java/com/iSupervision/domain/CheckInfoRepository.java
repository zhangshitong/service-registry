package com.iSupervision.domain;

import org.springframework.data.jpa.repository.JpaRepository;



public interface CheckInfoRepository extends JpaRepository<CheckInfo, Long> {

	CheckInfo findById(Long id);

}
