package com.fadel.demo.app.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.fadel.app.entities.EmployeeEntity;
import com.fadel.demo.app.models.AdvancedSearchModel;
import com.fadel.demo.app.models.EmployeeModel;
import com.fadel.demo.app.repository.IEmployeeRepository;
import com.fadel.demo.app.service.IEmployeeService;
import com.fadel.demo.app.service.IUtilities;
import com.speedment.jpastreamer.application.JPAStreamer;

import lombok.AllArgsConstructor;



/**
 * 
 * The service layer, is created to handle the buisnes requirments
 * 
 * */


@Service
@AllArgsConstructor
public class EmployeeService implements IEmployeeService{

	private final IEmployeeRepository repository;
	private final IUtilities util;
	private final JPAStreamer jpaStr;

	/**
	 * 
	 * Get al employess convert them to model and return them
	 * 
	 * */
	@Override
	public List<EmployeeModel> getAll() {
		// TODO Auto-generated method stub
		List<EmployeeEntity> entities = repository.findAll();
		List<EmployeeModel> list = util.mapList(entities, EmployeeModel.class);
		return list;
	}

	/**
	 * 
	 * Save entity and make sure that the max id is given to the new object
	 * 
	 * */
	@Override
	public EmployeeModel saveEntity(EmployeeModel model) {
		// TODO Auto-generated method stub
		Integer empId = repository.findMaxEmployeeId();
		empId = empId == null ? 0 : empId;
		EmployeeEntity entity = new EmployeeEntity(empId + 1);
		entity = (EmployeeEntity) util.convertObject(model, EmployeeEntity.class);
		entity.setId(empId + 1);
		entity = repository.save(entity);
		model = (EmployeeModel) util.convertObject(entity, EmployeeModel.class);
		return model;
	}

	@Override
	public void deleteEntity(EmployeeModel model) {
		// TODO Auto-generated method stub
		Optional<EmployeeEntity> op = repository.findById(model.getId());
		if(op.isPresent()) {
			EmployeeEntity entity = op.get();
			repository.delete(entity);
		}
	}

	
	/**
	 * 
	 * Advanced search funciton
	 * JPA stereamer will convert the filters to sql and execute them
	 * 
	 * */
	
	@Override
	public List<EmployeeModel> search(AdvancedSearchModel model) {
		// TODO Auto-generated method stub
		List<EmployeeEntity> entities =  jpaStr.stream(EmployeeEntity.class)
				.filter(emp-> {
					if(util.isStringEmpty(model.getName())) {
						return true;
					}else if(!util.isStringEmpty(model.getNameRadio())) {
						if("contain".equals(model.getNameRadio())) {
							return emp.getName().contains(model.getName());
						}else {
							return emp.getName().equals(model.getName());
						}
					}else{
						return emp.getName().equals(model.getName());
					}
				})
				.filter(emp-> {
					if(util.isStringEmpty(model.getFamilyName())) {
						return true;
					}else if(!util.isStringEmpty(model.getFamilyNameRadio())) {
						if("contain".equals(model.getFamilyNameRadio())) {
							return emp.getFamilyName().contains(model.getFamilyName());
						}else {
							return emp.getFamilyName().equals(model.getFamilyName());
						}
					}else{
						return emp.getFamilyName().equals(model.getFamilyName());
					}
				})
				.filter(emp ->{
					if(util.isStringEmpty(model.getGender()))
						return true;
					else {
						return model.getGender().equals(emp.getGender());
					}
				})
				.filter(emp -> {
					if(model.getAge()==0) {
						return true;
					}else {
						if(util.isStringEmpty(model.getAgeRadio()) || "equal".equals(model.getAgeRadio())) {
							return Integer.compare(model.getAge(), emp.getAge()) == 0 ? true : false;
						}else if("greater".equals(model.getAgeRadio())){
							return model.getAge() > emp.getAge() ? false : true;
						}else { 
							return model.getAge() < emp.getAge() ? false : true;
						}
					}
				})
				.filter(emp -> {
					if(model.getSalary() == 0) {
						return true;
					}else {
						if(util.isStringEmpty(model.getSalaryRadio()) || model.getSalaryRadio().equals("equal")) {
							return Integer.compare(model.getSalary(), emp.getSalary()) == 0 ? true : false;
						}else if("greater".equals(model.getSalaryRadio())) {
							return model.getSalary() > emp.getSalary() ? false : true;
						}else {
							return model.getSalary() < emp.getSalary() ? false : true;
						}
					}
				})
				.filter(emp ->{
					if(null != model.getStartDate()) {
						if("between".equals(model.getStartDateRadio()) && null != model.getSecondDate()) {
							return model.getStartDate().compareTo(emp.getStartDate()) * emp.getStartDate().compareTo(model.getSecondDate()) > 0 ? true : false;
						}else {
							if(util.isStringEmpty(model.getStartDateRadio())) {
								return model.getStartDate().equals(emp.getStartDate()) ? true : false;
							}else {
								if("greater".equals(model.getStartDateRadio())) {
									return model.getStartDate().compareTo(emp.getStartDate()) < 0 ? true : false; 
								}else if("lower".equals(model.getStartDateRadio())) {
									return model.getStartDate().compareTo(emp.getStartDate()) > 0 ? true : false;
								}else {
									return model.getStartDate().equals(emp.getStartDate()) ? true : false;
								}
							}
						}
					}else {
						return true;
					}
				})
				.collect(Collectors.toList());
		List<EmployeeModel> list = util.mapList(entities, EmployeeModel.class);
		return list;
	}

	@Override
	public List<EmployeeModel> getAllNameLike(String name) {
		// TODO Auto-generated method stub
		List<EmployeeEntity> entities = repository.findByNameContaining(name);
		List<EmployeeModel> list = util.mapList(entities, EmployeeModel.class);
		return list;
	}

	@Override
	public List<EmployeeModel> getAllNameIs(String name) {
		// TODO Auto-generated method stub
		List<EmployeeEntity> entities = repository.findByNameIs(name);
		List<EmployeeModel> list = util.mapList(entities, EmployeeModel.class);
		return list;
	}

	@Override
	public EmployeeModel getModelWithId() {
		// TODO Auto-generated method stub
		Integer empId = repository.findMaxEmployeeId();
		empId = empId == null ? 0 : empId;
		EmployeeModel model = new EmployeeModel();
		model.setId(empId+1);
		return model;
	}
	
}
