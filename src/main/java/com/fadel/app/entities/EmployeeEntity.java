package com.fadel.app.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="employee")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeEntity implements Serializable{

	@Id
	private Integer id;
	
	@Column
	private String name;
	
	@Column
	private String familyName;
	
	@Column(length = 1)
	private String gender;
	
	@Column
	private int age;
	
	@Column
	@Temporal(TemporalType.DATE)
	private Date startDate;
	
	@Column
	private int salary;
	
	public EmployeeEntity(Integer id) {
		// TODO Auto-generated constructor stub
		this.id = id;
	}
	
}
