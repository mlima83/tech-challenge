package com.pixeon.base.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import com.pixeon.base.dto.DefaultEntityDto;
import com.pixeon.base.model.DefaultEntity;

public abstract class DefaultEntityMapper<T, DTO> {
	
	public abstract DTO convertEntityToDto(T entity, DTO dto); 
	
	public abstract T convertDtoToEntity(DTO dto, T entity);
	
	public DefaultEntityDto convertDefaultEntityToDto(DefaultEntity entity, DefaultEntityDto dto) {
		if (dto == null) {
			dto = new DefaultEntityDto() {};
		}
		dto.setActive(entity.isActive());
		dto.setDtCreated(entity.getDtCreated());
		dto.setDtUpdated(entity.getDtUpdated());
		dto.setId(entity.getId());
		dto.setVersion(entity.getVersion());
		return dto;
	}
	
	public DefaultEntity convertDefaultDtoToEntity(DefaultEntityDto dto, DefaultEntity entity) {
		if (entity == null) {
			entity = new DefaultEntity() {};
		}
		entity.setActive(dto.isActive());
		entity.setDtCreated(dto.getDtCreated());
		entity.setDtUpdated(dto.getDtUpdated());
		entity.setId(dto.getId());
		entity.setVersion(dto.getVersion());
		return entity;
	}
	
	public List<T> convertListDtoToListEntity(List<DTO> listOfDtos) {
		if (listOfDtos == null) {
			return null;
		}
		return listOfDtos.parallelStream()
				.map(dto -> convertDtoToEntity(dto, null))
				.collect(Collectors.toList());
	}
	
	public List<DTO> convertListEntityToListDto(List<T> listOfEntity) {
		if (listOfEntity == null) {
			return null;
		}
		return listOfEntity.parallelStream()
				.map(entity -> convertEntityToDto(entity, null))
				.collect(Collectors.toList());
	}
	
	
	public Page<DTO> convertPageEntityToPageDto(Page<T> pageOfEntity) {
		if (pageOfEntity == null) {
			return null;
		}
		return new PageImpl<DTO>(
				convertListEntityToListDto(pageOfEntity.getContent()),
				pageOfEntity.getPageable(),
				pageOfEntity.getTotalElements());
	}

}
