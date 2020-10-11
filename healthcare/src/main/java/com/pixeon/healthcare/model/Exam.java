package com.pixeon.healthcare.model;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.pixeon.base.model.DefaultEntity;

@Entity
public class Exam extends DefaultEntity implements Transactionable {
	
	@NotNull
	@ManyToOne
	private Institution institution;
	
	@NotEmpty
	@Size(min = 3, max = 200)
	private String patientName;
	
	@NotEmpty
	private String patientNameCi;
	
	@Min(0)
	private int patientAge;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private Gender patientGender;
	
	@NotEmpty
	@Size(min = 3, max = 200)
	private String physicianName;
	
	@NotEmpty
	private String physicianNameCi;
	
	@NotEmpty
	private String physicianCRM;
	
	@NotEmpty
	private String procedureName;
	
	@NotEmpty
	private String procedureNameCi;

	public Institution getInstitution() {
		return institution;
	}

	public void setInstitution(Institution institution) {
		this.institution = institution;
	}

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

	public String getPatientNameCi() {
		return patientNameCi;
	}

	public void setPatientNameCi(String patientNameCi) {
		this.patientNameCi = patientNameCi;
	}

	public String getPhysicianNameCi() {
		return physicianNameCi;
	}

	public void setPhysicianNameCi(String physicianNameCi) {
		this.physicianNameCi = physicianNameCi;
	}

	public String getProcedureNameCi() {
		return procedureNameCi;
	}

	public void setProcedureNameCi(String procedureNameCi) {
		this.procedureNameCi = procedureNameCi;
	}

	@Override
	public String getKey() {
		return String.valueOf(getId());
	}

	@Override
	public String getBudgetKey() {
		return String.valueOf(this.institution.getId());
	}
	
	@Override
	public Map<String, String> getProperties() {
		Map<String, String> properties = new HashMap<String, String>();
		DebitOn debitOn = null;
		if (isNew()) {
			debitOn = DebitOn.CREATE;
		} else {
			debitOn = DebitOn.GET;
		}
		properties.put(DebitOn.PROPERTY_NAME, debitOn.toString());
		return properties;
	}

}
