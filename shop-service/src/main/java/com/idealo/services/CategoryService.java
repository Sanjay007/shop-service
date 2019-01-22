package com.idealo.services;

import com.idealo.input.CategoryInput;
import com.idealo.output.APIResponse;

public interface CategoryService {
	APIResponse saveCategory(CategoryInput input);
	APIResponse getAllcategory() ;
}
