package com.pixeon.healthcare.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.pixeon.base.dto.DefaultEntityDto;

public class InstitutionDtoV1 extends DefaultEntityDto{
	
	@NotEmpty
	@Size(min = 3, max = 200)
	private String name;

	@NotEmpty
	@Size(min = 14, max = 14)
	private String cnpj;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

}
