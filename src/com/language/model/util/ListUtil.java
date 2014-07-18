package com.language.model.util;

import java.util.ArrayList;

public class ListUtil<T> {
	
	private ArrayList<T> list = new ArrayList<T>();
		
	public ListUtil() {
		
	}
	
	public ListUtil(T t) {
		list.add(t);
	}
	
	public ListUtil<T> append(T t) {
		list.add(t);
		return this;
	}
	
	public ArrayList<T> getList() {
		return list;
	}
	
	public int size() {
		return list.size();
	}
}
