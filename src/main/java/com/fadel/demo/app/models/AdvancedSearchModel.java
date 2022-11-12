package com.fadel.demo.app.models;

import java.util.Date;

import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdvancedSearchModel {
	private Integer id;
	private String name;
	private String familyName;
	private String gender;
	private int age;
	private Date startDate;
	private int salary;
	private String nameRadio;
	private String familyNameRadio;
	private String ageRadio;
	private String startDateRadio;
	private String salaryRadio;
	private Date secondDate;
	
}
