package com.idealo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.idealo.input.CategoryInput;
import com.idealo.output.APIResponse;
import com.idealo.services.CategoryService;
import com.idealo.constants.AppConstants;
@RestController
public class CategoryController {
	
	private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);

	private final CategoryService categoryService;

	public CategoryController(CategoryService categoryService) {

		this.categoryService = categoryService;
	}

	/**
	 * Category Request
	 * @param input
	 * @return
	 */
	@PostMapping(AppConstants.R_CATEGORY)
	@CrossOrigin(origins = AppConstants.CORS)
	public APIResponse SaveCategory(@RequestBody CategoryInput input) {

		logger.info("Category Save Request Initialized");
		return categoryService.saveCategory(input);
	}

	@GetMapping(AppConstants.R_CATEGORY)
	@CrossOrigin(origins = AppConstants.CORS)
	public APIResponse getAllCategory() {

		logger.info("Category retrieve Request Initialized");
		return categoryService.getAllcategory();
	}
}
