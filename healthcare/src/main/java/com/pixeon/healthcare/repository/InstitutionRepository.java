package com.pixeon.healthcare.repository;

import com.pixeon.healthcare.repository.basic.InstitutionRepositoryBasic;
import com.pixeon.healthcare.repository.custom.InstitutionRepositoryCustom;

public interface InstitutionRepository extends InstitutionRepositoryCustom, InstitutionRepositoryBasic {

//	@Query("SELECT * FROM case WHERE folder >= :folder AND folder <= :folder ")
//	public Page<Case> findByFolder(@Param("folder") String folder, Pageable pageable);
}
