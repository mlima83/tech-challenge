package com.pixeon.healthcare.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.pixeon.base.model.DefaultEntity;

@Entity
public class Institution extends DefaultEntity implements Budgetable {

	@NotEmpty
	@Size(min = 3, max = 200)
	private String name;
	
	@NotEmpty
	private String nameCi;

	@NotEmpty
	@Size(min = 14, max = 14)
	@Pattern(regexp = "[0-9]*")
	@Column(unique = true)
	private String cnpj;
	
	public Institution() {}
	
	public Institution(Long id) {
		setId(id);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNameCi() {
		return nameCi;
	}

	public void setNameCi(String nameCi) {
		this.nameCi = nameCi;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	@Override
	public String getKey() {
		return String.valueOf(getId());
	}

}
