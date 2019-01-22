package com.idealo.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.idealo.input.ItemInput;
import com.idealo.output.APIResponse;
import com.idealo.services.ItemService;
import com.idealo.constants.AppConstants;

@RestController
public class ItemsController {

	private final ItemService itemService;

	public ItemsController(ItemService itemService) {
		this.itemService = itemService;
	}

	@PostMapping(AppConstants.R_ITEMS)
	@CrossOrigin(origins = AppConstants.CORS)
	public APIResponse addItem(@RequestBody ItemInput input) {

		return itemService.saveItem(input);
	}

	@GetMapping(AppConstants.R_CATEGORY+"/{CategoryId}"+AppConstants.R_ITEMS)
	@CrossOrigin(origins = AppConstants.CORS)
	public APIResponse getAllitems(@PathVariable("CategoryId") Long id) {

		return itemService.getItems(id);
	}

}
