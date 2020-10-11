package com.pixeon.healthcare.repository.basic;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.pixeon.healthcare.model.Institution;

public interface InstitutionRepositoryBasic extends PagingAndSortingRepository<Institution,Long> {

}
