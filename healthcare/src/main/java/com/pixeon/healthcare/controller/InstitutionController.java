package com.pixeon.healthcare.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pixeon.base.controller.DefaultController;
import com.pixeon.base.mapper.DefaultEntityMapper;
import com.pixeon.base.service.DefaultService;
import com.pixeon.healthcare.dto.InstitutionDtoV1;
import com.pixeon.healthcare.mapper.InstitutionMapper;
import com.pixeon.healthcare.model.Institution;
import com.pixeon.healthcare.service.IntitutionService;

@RestController
@RequestMapping("institutions")
@Validated
public class InstitutionController extends DefaultController<Institution, Long, InstitutionDtoV1> {
	
	@Autowired
	private IntitutionService institutionService;
	
	@Autowired
	private InstitutionMapper institutionMapper;
	
	@Override
	public DefaultService<Institution, Long> getService() {
		return this.institutionService;
	}

	@Override
	public DefaultEntityMapper<Institution, InstitutionDtoV1> getMapper() {
		return this.institutionMapper;
	}

}
