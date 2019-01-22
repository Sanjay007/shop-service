package com.idealo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.idealo.models.Item;

public interface ItemRepository  extends JpaRepository<Item, Long>{

	Item findByTitle(String title);
}
