package com.fadel.demo.app.service;

import java.util.List;

import com.fadel.demo.app.models.AdvancedSearchModel;
import com.fadel.demo.app.models.EmployeeModel;

public interface IEmployeeService {
	List<EmployeeModel> getAll();
	EmployeeModel saveEntity(EmployeeModel model);
	void deleteEntity(EmployeeModel model);
	List<EmployeeModel> search(AdvancedSearchModel model);
	List<EmployeeModel> getAllNameLike(String name);
	List<EmployeeModel> getAllNameIs(String name);
	EmployeeModel getModelWithId();
}
