package com.codeMst.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface UnitRepository extends JpaRepository<Unit, Long> {

	Unit findById(Long id);

    @Query("from Unit u where u.unitName=:unitName")
    Unit findUnit(@Param("unitName") String unitName);

}
