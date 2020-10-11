package com.pixeon.healthcare.dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.pixeon.base.dto.DefaultEntityDto;
import com.pixeon.healthcare.model.Gender;

public class ExamDtoV1 extends DefaultEntityDto{
	
	@NotNull
	private Long institutionId;
	
	@NotEmpty
	@Size(min = 3, max = 200)
	private String patientName;
	
	@Min(0)
	private int patientAge;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private Gender patientGender;
	
	@NotEmpty
	@Size(min = 3, max = 200)
	private String physicianName;
	
	@NotEmpty
	private String physicianCRM;
	
	@NotEmpty
	private String procedureName;

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public int getPatientAge() {
		return patientAge;
	}

	public void setPatientAge(int patientAge) {
		this.patientAge = patientAge;
	}

	public Gender getPatientGender() {
		return patientGender;
	}

	public void setPatientGender(Gender patientGender) {
		this.patientGender = patientGender;
	}

	public String getPhysicianName() {
		return physicianName;
	}

	public void setPhysicianName(String physicianName) {
		this.physicianName = physicianName;
	}

	public String getPhysicianCRM() {
		return physicianCRM;
	}

	public void setPhysicianCRM(String physicianCRM) {
		this.physicianCRM = physicianCRM;
	}

	public String getProcedureName() {
		return procedureName;
	}

	public void setProcedureName(String procedureName) {
		this.procedureName = procedureName;
	}

	public Long getInstitutionId() {
		return institutionId;
	}

	public void setInstitutionId(Long institutionId) {
		this.institutionId = institutionId;
	}

}
