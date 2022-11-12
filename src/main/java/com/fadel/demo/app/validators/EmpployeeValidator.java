package com.fadel.demo.app.validators;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.fadel.demo.app.constant.ConstantCommon;
import com.fadel.demo.app.models.EmployeeModel;
import com.fadel.demo.app.service.IUtilities;

import lombok.AllArgsConstructor;

/**
 * 
 * This claa will validate the inputs which are not anotated in the model
 * */

@Component
@AllArgsConstructor
public class EmpployeeValidator implements Validator{

	public final IUtilities util;
	
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return EmployeeModel.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		EmployeeModel model = (EmployeeModel) target;
		
		if(util.isStringContainNumber(model.getName())) {
			errors.rejectValue("name", "required.name.withoutnumber");
		}
		
		if(util.isStringContainNumber(model.getFamilyName())) {
			errors.rejectValue("familyName", "required.familyName.withoutnumber");
		}
		
		if(!util.isStringEmpty(model.getGender())) {
			if(!model.getGender().equals("F") && !model.getGender().equals("M")) {
				errors.rejectValue("gender", "required.gender.onlyForM");
			}
		}
		
		if(null == model.getStartDate()) {
			errors.rejectValue("gender", "required.date.dateIsRequired");
		}else {
			SimpleDateFormat sdfo= new SimpleDateFormat(ConstantCommon.DATE_PATTERN);
			try {
				Date date = sdfo.parse(ConstantCommon.MINIMUM_ACCEPTABLE_DATE);
				if(model.getStartDate().compareTo(date)<0) {
					errors.rejectValue("startDate", "required.date.dateValidity");
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}


		
	}

}
