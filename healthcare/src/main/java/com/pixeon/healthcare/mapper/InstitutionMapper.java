package com.pixeon.healthcare.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pixeon.base.mapper.DefaultEntityMapper;
import com.pixeon.base.utils.Utils;
import com.pixeon.healthcare.dto.InstitutionDtoV1;
import com.pixeon.healthcare.model.Institution;

@Component
public class InstitutionMapper extends DefaultEntityMapper<Institution, InstitutionDtoV1>{
	
	@Autowired
	private Utils utils;

	@Override
	public InstitutionDtoV1 convertEntityToDto(Institution entity, InstitutionDtoV1 dto) {
		if (dto == null) {
			dto = new InstitutionDtoV1();
		}
		dto.setName(entity.getName());
		dto.setCnpj(entity.getCnpj());
		
		return (InstitutionDtoV1) convertDefaultEntityToDto(entity, dto);
	}

	@Override
	public Institution convertDtoToEntity(InstitutionDtoV1 dto, Institution entity) {
		if (entity == null) {
			entity = new Institution();
		}
		entity.setName(dto.getName());
		if (dto.getName() != null && !dto.getName().isEmpty()) {
			entity.setNameCi(utils.transformUnaccentAndCaseInsensitive(dto.getName()));
		}
		entity.setCnpj(dto.getCnpj());
		
		return (Institution) convertDefaultDtoToEntity(dto, entity);
	}


}
