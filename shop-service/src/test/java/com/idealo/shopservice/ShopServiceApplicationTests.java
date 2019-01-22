package com.idealo.shopservice;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.idealo.input.CategoryInput;
import com.idealo.models.Category;
import com.idealo.output.APIResponse;
import com.idealo.output.CategoryOutPut;
import com.idealo.repository.CategoryRepository;
import com.idealo.services.CategoryService;
import com.idealo.services.impl.CategoryServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ShopServiceApplicationTests {

	private CategoryRepository categoryRepository;
	private CategoryService categoryService;
	private Category mockCategory;

	@Before
	public void setup() {
		categoryRepository = Mockito.mock(CategoryRepository.class);
		mockCategory = Mockito.mock(Category.class);
		categoryService = new CategoryServiceImpl(categoryRepository);
	}

	@Test
	public void testEmptyInput() {

		APIResponse api = categoryService.saveCategory(new CategoryInput());
		assert (api.getErrorMsgEn().equalsIgnoreCase("Input parameters are not correct"));

		assertThat(api.getErrorMsgEn()).isNotNull().isNotEmpty().hasToString("Input parameters are not correct");

	}

	@Test
	public void testExistingCategory() {
		CategoryInput input = new CategoryInput();
		input.setTitle("TEST_CATEG");

		when(categoryRepository.findByTitle(any(String.class))).thenReturn(mockCategory);
		APIResponse api = categoryService.saveCategory(input);
		assert (api.getErrorMsgEn().equalsIgnoreCase("Category Already Present !"));
	}

	@Test
	public void testNewCategory() {
		CategoryInput input = new CategoryInput();
		input.setTitle("TEST_CATEG");

		when(categoryRepository.findByTitle(any(String.class))).thenReturn(null);
		when(categoryRepository.save(any(Category.class))).thenReturn(mockCategory);

		APIResponse api = categoryService.saveCategory(input);
		assert (api.isSuccess());
	}

	@Test
	public void testGetAllCategory() {
		List<Category> listOfCateg = new ArrayList<>();

		listOfCateg.add(mockCategory);

		when(mockCategory.getId()).thenReturn(1L);
		when(mockCategory.getTitle()).thenReturn("TEST_TITLE");
		when(categoryRepository.findAll()).thenReturn(listOfCateg);

		CategoryInput input = new CategoryInput();
		input.setTitle("TEST_CATEG");

		APIResponse api = categoryService.getAllcategory();
		List<CategoryOutPut> allOut = (List<CategoryOutPut>) api.getData();
		assert (api.isSuccess());

		assert (allOut.size() > 0);
		assertThat(allOut).isNotEmpty();
	}

}
