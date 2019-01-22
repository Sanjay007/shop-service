package com.idealo.services.impl;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.idealo.input.ItemInput;
import com.idealo.models.Category;
import com.idealo.models.Item;
import com.idealo.output.APIResponse;
import com.idealo.output.ItemOut;
import com.idealo.repository.CategoryRepository;
import com.idealo.repository.ItemRepository;
import com.idealo.services.ItemService;
import com.idealo.utils.ErrorCode;

@Service
@Qualifier("itemService")
public class ItemServiceimpl implements ItemService {

	private final ItemRepository itemRepository;

	private final CategoryRepository categoryRepository;

	public ItemServiceimpl(ItemRepository itemRepository, CategoryRepository categoryRepository) {

		this.itemRepository = itemRepository;
		this.categoryRepository = categoryRepository;
	}

	/**
	 * Save Item Service
	 *@param input
	 *return APIResponse
	 */
	@Override
	public APIResponse saveItem(ItemInput input) {

		APIResponse response = ValidateInput(input);

		if (!response.isSuccess()) {
			return response;
		}
		Category dbCategory = categoryRepository.findById(input.getCatId()).orElse(null);

		Item dbItem = new Item();
		dbItem.setItem(dbCategory);
		BeanUtils.copyProperties(input, dbItem);

		Item savedItem = itemRepository.save(dbItem);
		ItemOut respCategory = new ItemOut();
		BeanUtils.copyProperties(savedItem, respCategory);

		response.setData(respCategory);

		return response;

	}

	@Override
	public APIResponse getItems(Long id) {

		Category dbCategory = categoryRepository.findById(id).orElse(null);

		if (dbCategory != null) {

			Set<Item> allDBItems = dbCategory.getItems();

			List<ItemOut> allRespItems = allDBItems.stream()
					.map(p -> new ItemOut(p.getId(), p.getTitle(), p.getText(), p.getPrice()))
					.collect(Collectors.toList());

			return new APIResponse(allRespItems);

		} else {
			return new APIResponse(ErrorCode.INVALID_CATEGORY, String.valueOf(id));
		}

	}

	/**
	 * Validate Null inputs and category 
	 * @param input
	 * @return
	 */
	private APIResponse ValidateInput(ItemInput input) {

		if (StringUtils.isEmpty(input.getCatId()) || StringUtils.isEmpty(input.getPrice())
				|| StringUtils.isEmpty(input.getText()) || StringUtils.isEmpty(input.getTitle())) {
			return new APIResponse(ErrorCode.INPUT_PARAM_NOT_CORRECT);

		}

		Category cat = categoryRepository.findById(input.getCatId()).orElse(null);
		Item dbItem = itemRepository.findByTitle(input.getTitle());

		if (dbItem != null) {
			return new APIResponse(ErrorCode.ITEM_ALREADY_EXISTS);
		}
		if (cat == null) {
			return new APIResponse(ErrorCode.INVALID_CATEGORY, String.valueOf(input.getCatId()));
		}

		return new APIResponse();
	}
}
