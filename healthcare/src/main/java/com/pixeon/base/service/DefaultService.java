package com.pixeon.base.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.pixeon.base.dto.ErrorItemDtoV1;
import com.pixeon.base.exception.BusinessException;
import com.pixeon.base.model.DefaultEntity;
import com.pixeon.base.model.StateChange;

public abstract class DefaultService<T extends DefaultEntity, Key> {
	
	public abstract PagingAndSortingRepository<T, Key> getRepository();
	
	private List<ErrorItemDtoV1> errors;

	public List<T> findAll() {
		return (List<T>) getRepository().findAll();
	}

	public Page<T> findAllPagination(Pageable pageable) {
		return getRepository().findAll(pageable);
	}

	public T findById(Key id) {
		Optional<T> retorno = getRepository().findById(id);
		if (retorno.isPresent()) {
			return retorno.get();
		} else {
			return null;
		}
	}

	public long count() {
		return getRepository().count();
	}

	@Transactional
	public T save(@Valid T t) throws BusinessException {
		if (t == null) {
			throw new BusinessException("Entity must not be empty");
		}
		clearErrors();
		t.setStateChange(t.getId() == null ? StateChange.INSERT : StateChange.UPDATE);
		beforeSave(t);
     	if (hasErrors()) {
     		throw new BusinessException(this.errors);
     	}
		t.setDtUpdated(new Date());
		if (t.getId() == null || t.getDtCreated() == null) {
			t.setDtCreated(new Date());
		}
		T saved = (T) getRepository().save(t);
		afterSave(t);
		return saved;
	}

	/** Run something before saving or custom validate */
	public void beforeSave(T t) throws BusinessException {}
	
	/** Run something after saved */
	public void afterSave(T t) throws BusinessException {}

	@Transactional
	public List<T> saveAll(@NotEmpty @RequestBody List<@Valid T> listOfAll) {
		return (List<T>) getRepository().saveAll(listOfAll);
	}

	public void deleteAll() {
		getRepository().deleteAll();
	}

	public void deleteById(Key id) {
		getRepository().deleteById(id);
	}
	
	protected void addError(String key, String message) {
		if (this.errors == null) {
			clearErrors();
		}
		this.errors.add(new ErrorItemDtoV1(key, message));
	}
	
	public void clearErrors() {
		this.errors = new ArrayList<ErrorItemDtoV1>();
	}
	
	public boolean hasErrors() {
		return this.errors != null && !this.errors.isEmpty();
	}

}
