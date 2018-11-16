package com.teamsec.server.demo.repository.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.teamsec.server.demo.entity.Food;

public interface FoodRepository extends JpaRepository<Food, String>, JpaSpecificationExecutor<Food> {

	Food findById(Long id);
}
