package com.redhood.fornebu_pizza_backend.repository;

import com.redhood.fornebu_pizza_backend.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
}
