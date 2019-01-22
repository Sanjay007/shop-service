package com.idealo.output;

import java.util.ArrayList;
import java.util.List;

public class CategoryOutPut {

	Long id ;
	String title;
	
	public CategoryOutPut() {
		
	}
	public CategoryOutPut(Long id, String title) {
		
		this.id = id;
		this.title = title;
	}
	
	
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
	
}

