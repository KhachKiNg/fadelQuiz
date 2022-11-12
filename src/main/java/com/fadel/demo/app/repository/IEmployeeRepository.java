package com.fadel.demo.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fadel.app.entities.EmployeeEntity;

/**
 * 
 * Employee repository extends the JPA repo so all method could be called without implementations, we can create some new functions using specific syntax without having to implement them 
 * 
 * */

public interface IEmployeeRepository extends JpaRepository<EmployeeEntity, Integer>{
	@Query(value = "SELECT MAX(ID) FROM EMPLOYEE" ,	nativeQuery = true)
	Integer findMaxEmployeeId();
	List<EmployeeEntity> findByNameContaining(String name);
	List<EmployeeEntity> findByNameIs(String name);
}
