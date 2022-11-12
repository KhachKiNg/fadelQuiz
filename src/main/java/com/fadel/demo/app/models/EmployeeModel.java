package com.fadel.demo.app.models;

import java.io.Serializable;
import java.util.Date;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeModel implements Serializable{
	
	private Integer id;
	@NotBlank @Size(min=3, max=30)
	private String name;
	@NotBlank @Size(min=3, max=30)
	private String familyName;
	@Size(max=1)
	private String gender;
	@NotNull
	@Positive
	private int age;
	@NotNull
	private Date startDate;
	@NotNull
	@Positive
	private int salary;
}
