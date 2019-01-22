package com.idealo.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.idealo.input.CategoryInput;
import com.idealo.models.Category;
import com.idealo.output.APIResponse;
import com.idealo.output.CategoryOutPut;
import com.idealo.repository.CategoryRepository;
import com.idealo.services.CategoryService;
import com.idealo.utils.ErrorCode;

@Service
@Qualifier("categoryService")
public class CategoryServiceImpl implements CategoryService {

	private static final Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);

	CategoryRepository categoryRepository;

	public CategoryServiceImpl(CategoryRepository categoryRepository) {

		this.categoryRepository = categoryRepository;
	}

	public APIResponse saveCategory(CategoryInput input) {
		APIResponse response = ValidateInput(input);

		if (!response.isSuccess()) {
			return response;
		}
		logger.info("Saving  Category");

		Category dbCategory = new Category();
		BeanUtils.copyProperties(input, dbCategory);
		Category categorySaved = categoryRepository.save(dbCategory);

		CategoryOutPut responseCategory = new CategoryOutPut();
		BeanUtils.copyProperties(categorySaved, responseCategory);
		response.setData(responseCategory);
		return response;

	}

	public APIResponse getAllcategory() {
		logger.info("Retrieving All Category");
		List<Category> allDBCategory = categoryRepository.findAll();

		List<CategoryOutPut> allRespCategories = allDBCategory.stream()
				.map(p -> new CategoryOutPut(p.getId(), p.getTitle())).collect(Collectors.toList());

		return new APIResponse(allRespCategories);
	}

	private APIResponse ValidateInput(CategoryInput input) {

		if (StringUtils.isEmpty(input.getTitle())) {
			logger.debug("Input Values are not Proper !");

			return new APIResponse(ErrorCode.INPUT_PARAM_NOT_CORRECT);
		}

		Category dbCategory = categoryRepository.findByTitle(input.getTitle());

		if (dbCategory != null) {
			logger.debug("Category Code Already Exists !");
			return new APIResponse(ErrorCode.CATEGORY_ALREADY_EXISTS, input.getTitle());
		}

		return new APIResponse();
	}

}
