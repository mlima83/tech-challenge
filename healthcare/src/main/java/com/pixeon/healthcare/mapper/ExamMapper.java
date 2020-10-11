package com.pixeon.healthcare.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pixeon.base.mapper.DefaultEntityMapper;
import com.pixeon.base.utils.Utils;
import com.pixeon.healthcare.dto.ExamDtoV1;
import com.pixeon.healthcare.model.Exam;
import com.pixeon.healthcare.model.Institution;

@Component
public class ExamMapper extends DefaultEntityMapper<Exam, ExamDtoV1>{
	
	@Autowired
	private Utils utils;
	
	@Override
	public ExamDtoV1 convertEntityToDto(Exam entity, ExamDtoV1 dto) {
		if (entity == null) {
			return null;
		}
		if (dto == null) {
			dto = new ExamDtoV1();
		}
		if (entity.getInstitution() != null) {
			dto.setInstitutionId(entity.getInstitution().getId());
		}
		dto.setPatientName(entity.getPatientName());
		dto.setPatientAge(entity.getPatientAge());
		dto.setPatientGender(entity.getPatientGender());
		dto.setPhysicianName(entity.getPhysicianName());
		dto.setPhysicianCRM(entity.getPhysicianCRM());
		dto.setProcedureName(entity.getProcedureName());
		return (ExamDtoV1) convertDefaultEntityToDto(entity, dto);
	}

	@Override
	public Exam convertDtoToEntity(ExamDtoV1 dto, Exam entity) {
		if (entity == null) {
			entity = new Exam();
		}
		entity.setInstitution(new Institution(dto.getInstitutionId()));
		entity.setPatientName(dto.getPatientName());
		if (dto.getPatientName() != null && !dto.getPatientName().isEmpty()) {
			entity.setPatientNameCi(utils.transformUnaccentAndCaseInsensitive(dto.getPatientName()));
		}
		entity.setPatientAge(dto.getPatientAge());
		entity.setPatientGender(dto.getPatientGender());
		entity.setPhysicianName(dto.getPhysicianName());
		if (dto.getPhysicianName() != null && !dto.getPhysicianName().isEmpty()) {
			entity.setPhysicianNameCi(utils.transformUnaccentAndCaseInsensitive(dto.getPhysicianName()));
		}
		entity.setPhysicianCRM(dto.getPhysicianCRM());
		entity.setProcedureName(dto.getProcedureName());
		if (dto.getProcedureName() != null && !dto.getProcedureName().isEmpty()) {
			entity.setProcedureNameCi(utils.transformUnaccentAndCaseInsensitive(dto.getProcedureName()));
		}
		
		return (Exam) convertDefaultDtoToEntity(dto, entity);
	}


}
