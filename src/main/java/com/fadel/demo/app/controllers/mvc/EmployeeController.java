package com.fadel.demo.app.controllers.mvc;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.fadel.demo.app.constant.ConstantCommon;
import com.fadel.demo.app.models.AdvancedSearchModel;
import com.fadel.demo.app.models.EmployeeModel;
import com.fadel.demo.app.service.IEmployeeService;
import com.fadel.demo.app.service.IUtilities;
import com.fadel.demo.app.validators.EmpployeeValidator;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/Fadel/employee")
@RequiredArgsConstructor
public class EmployeeController {
	
	public final ModelMapper mapper;
	public final IEmployeeService service;
	public final EmpployeeValidator employeeValidator;
	public final IUtilities util;
	
	
	/**
	 * 
	 * Binder is a layer inside the controller used to manage the validators and the conversions between views and controllers
	 * 
	 * */
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        if(binder.getObjectName().equals(ConstantCommon.BINDER_EMPLOYE_OBJECT_NAME)) {
        	SimpleDateFormat dateFormat = new SimpleDateFormat(ConstantCommon.DATE_PATTERN);
            dateFormat.setLenient(true);
            binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
        	binder.addValidators(employeeValidator);
        }else {
        	SimpleDateFormat dateFormat = new SimpleDateFormat(ConstantCommon.DATE_PATTERN);
            dateFormat.setLenient(false);
            binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
        }
    }
	
	/**
	 * 
	 * This method is to get the view
	 * 
	 * */

	@RequestMapping(value = "*", method = RequestMethod.GET)
	public ModelAndView index() {
		List<EmployeeModel> list = service.getAll();
		ModelAndView view = new ModelAndView("index","list",list);
		view.addObject("employee", service.getModelWithId());
		view.addObject("searchEmployee", new AdvancedSearchModel());
		return view;
	}
	
	/**
	 * 
	 * This method will be called once the simple search is made
	 * It can be used to find an employee by name or an employee which his name contain the searched value
	 * 
	 * **/
	
	@RequestMapping(value = {"/search/{name}","/search/{name}/{filter}"}, method = RequestMethod.GET)
	public ModelAndView search(@PathVariable(name="name") String name,@PathVariable(name="filter",required = false) String filter) {
		List<EmployeeModel> list;
		if(util.isStringEmpty(name)) {
			list = service.getAll();
		}else {
			if(!util.isStringEmpty(filter) && filter.equals("like")){
				list = service.getAllNameLike(name);
			}else {
				list = service.getAllNameIs(name);
			}
		}
		ModelAndView view = new ModelAndView("index","list",list);
		view.addObject("employee", service.getModelWithId());
		view.addObject("searchEmployee", new AdvancedSearchModel());
		return view;
	}
	
	/**
	 * 
	 * This method will check all the required values, once every input is validated it will continue to save the entity
	 * If not it will return back the view conatining the errors
	 * 
	 * */
	
	@RequestMapping(value = "*", method = RequestMethod.POST,params = "submit")
	public String add(@Valid @ModelAttribute("employee") EmployeeModel model,BindingResult bindingResult,ModelMap map) {
		
		if(bindingResult.hasErrors()) {
			map.addAttribute("searchEmployee", new AdvancedSearchModel());
			List<EmployeeModel> list = service.getAll();
			map.addAttribute("list", list);
			return "index";
		}
		
		model = service.saveEntity(model);
		return "redirect:/Fadel/employee/";
	}
	
	/**
	 * 
	 * This method is called once the advanced search is used
	 * 
	 * */
	@RequestMapping(value = "*", method = RequestMethod.POST,params = "search")
	public ModelAndView search(@Valid @ModelAttribute("searchEmployee") AdvancedSearchModel model) {
		List<EmployeeModel> list = service.search(model);
		ModelAndView view = new ModelAndView("index","list",list);
		view.addObject("employee", service.getModelWithId());
		view.addObject("searchEmployee", new AdvancedSearchModel());
		return view;
	}
	
	@RequestMapping(value = "*", method = RequestMethod.POST,params = "delete")
	public String delete(@Valid @ModelAttribute("employee") EmployeeModel model,BindingResult bindingResult) {
		
		service.deleteEntity(model);
		return "redirect:/Fadel/employee/";
	}
}
