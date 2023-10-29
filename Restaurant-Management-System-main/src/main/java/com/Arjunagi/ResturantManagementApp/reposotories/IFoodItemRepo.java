package com.Arjunagi.ResturantManagementApp.reposotories;

import com.Arjunagi.ResturantManagementApp.models.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFoodItemRepo extends JpaRepository<FoodItem,Integer> {
}
