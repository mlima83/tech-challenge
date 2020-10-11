package com.pixeon.healthcare.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pixeon.base.controller.DefaultController;
import com.pixeon.base.exception.BusinessException;
import com.pixeon.base.mapper.DefaultEntityMapper;
import com.pixeon.base.service.DefaultService;
import com.pixeon.healthcare.dto.ExamDtoV1;
import com.pixeon.healthcare.mapper.ExamMapper;
import com.pixeon.healthcare.model.Exam;
import com.pixeon.healthcare.service.ExamService;

@RestController
@RequestMapping("exams")
@Validated
public class ExamController extends DefaultController<Exam, Long, ExamDtoV1> {
	
	@Autowired
	private ExamService examService;
	
	@Autowired
	private ExamMapper examMapper;
	
	@GetMapping("{institutionId}/{examId}")
	public ExamDtoV1 findById(@PathVariable("institutionId") Long institutionId, @PathVariable("examId") Long examId) throws BusinessException {
		return getMapper().convertEntityToDto(examService.findByExamIdAndIstitutionId(examId, institutionId), null);
	}
	
	@DeleteMapping("{institutionId}/{examId}")
	public void deleteById(@PathVariable("institutionId") Long institutionId, @PathVariable("examId") Long examId) throws BusinessException {
		examService.deleteByExamIdAndIstitutionId(examId, institutionId);
	}
	
	@Override
	public long count() {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public void deleteAll() {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public void deleteById(Long id) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public Page<ExamDtoV1> findAllPagination(Pageable pageable) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public List<ExamDtoV1> findAll() {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public ExamDtoV1 findById(Long id) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public DefaultService<Exam, Long> getService() {
		return this.examService;
	}

	@Override
	public DefaultEntityMapper<Exam, ExamDtoV1> getMapper() {
		return this.examMapper;
	}

}
