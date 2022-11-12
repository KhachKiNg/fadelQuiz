package com.fadel.demo.app.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.fadel.demo.app.service.IUtilities;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class Utilities implements IUtilities{
	
	public final ModelMapper mapper;

	@Override
	public <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
		// TODO Auto-generated method stub
		if(source.isEmpty())
			return null;

		return source
	      .stream()
	      .map(element -> mapper.map(element, targetClass))
	      .collect(Collectors.toList());
	}

	@Override
	public <T> Object convertObject(Object obj, Class<T> targetClass) {
		// TODO Auto-generated method stub
		Object returnedObject = mapper.map(obj, targetClass);
		return returnedObject;
	}

	@Override
	public boolean isStringContainNumber(String s) {
		// TODO Auto-generated method stub
		if(isStringEmpty(s)) {
			return false;
		}
		char [] chars = s.toCharArray();
		for(int i=0;i<chars.length;i++) {
			if(Character.isDigit(chars[i]))
				return true;
		}
		return false;
	}

	@Override
	public boolean isStringEmpty(String s) {
		// TODO Auto-generated method stub
		if(null != s && !s.isEmpty() && !s.isBlank())
			return false;
		return true;
	}


}
