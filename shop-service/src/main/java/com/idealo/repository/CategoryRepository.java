package com.idealo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.idealo.models.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

	Category findByTitle(String title);
}
