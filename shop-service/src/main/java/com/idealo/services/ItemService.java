package com.idealo.services;

import com.idealo.input.ItemInput;
import com.idealo.output.APIResponse;


public interface ItemService {
	 APIResponse saveItem(ItemInput input);
	 APIResponse getItems(Long id) ;
}
