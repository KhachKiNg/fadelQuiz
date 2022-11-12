package com.fadel.demo.app.service;

import java.util.List;


public interface IUtilities {
	public <S, T> List<T> mapList(List<S> source, Class<T> targetClass);
	public <T> Object convertObject(Object obj,Class<T> targetClass);
	public boolean isStringContainNumber(String s);
	public boolean isStringEmpty(String s);
}
