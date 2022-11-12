package com.fadel.demo.app.configuration;

import javax.faces.webapp.FacesServlet;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@EnableWebMvc
@Configuration
public class WebConfig implements WebMvcConfigurer{
	
	/**
	 * 
	 * Creating beans to select view type JSP not HTML or any other types of views
	 * Adding the location of these views
	 * 
	 * */ 
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver bean = new InternalResourceViewResolver();
		bean.setViewClass(JstlView.class);
		bean.setPrefix("/WEB-INF/view/");
		bean.setSuffix(".jsp");
		return bean;
	}
	
	/**
	 * 
	 * Create bean to add the properties file which contains the messages to be used by the validator
	 * 
	 * */
	@Bean
	public ResourceBundleMessageSource messageSource() {
		ResourceBundleMessageSource src = new ResourceBundleMessageSource();
		src.setBasename("messages/messages");
		src.setUseCodeAsDefaultMessage(true);
		return src;
	}

	/**
	 * 
	 * Creating bean to initialize model mapper used to convert between entities and models
	 * 
	 * */
	@Bean
	public ModelMapper modelMapper() {
		ModelMapper mapper = new ModelMapper();
		
		mapper.getConfiguration()
		.setFieldMatchingEnabled(Boolean.TRUE)
		.setFieldAccessLevel(AccessLevel.PRIVATE)
		.setMethodAccessLevel(AccessLevel.PRIVATE);
		
		return mapper;
	} 
	
	@Bean
	 public ServletRegistrationBean servletRegistrationBean() {
        FacesServlet servlet = new FacesServlet();
        ServletRegistrationBean servletRegistrationBean =
                new ServletRegistrationBean(servlet, "*.jsf");
        return servletRegistrationBean;
    } 
	
}
