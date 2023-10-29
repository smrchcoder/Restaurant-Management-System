package com.Arjunagi.ResturantManagementApp.controllers;

import com.Arjunagi.ResturantManagementApp.models.FoodItem;
import com.Arjunagi.ResturantManagementApp.models.dto.AuthInpDto;
import com.Arjunagi.ResturantManagementApp.models.dto.FoodDto;
import com.Arjunagi.ResturantManagementApp.services.FoodItemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FoodItemController {
    @Autowired
    FoodItemService foodItemService;

    @PostMapping("/food/admin")//
    public String addFood(@Valid @RequestBody FoodDto foodDto){
        return foodItemService.addFood(foodDto);
    }

    @GetMapping("/food/all")
    public List<FoodItem> getAll(){
        return foodItemService.getAll();
    }
    @PutMapping("/food/id/{id}/admin")
    public String updateFoodById(@RequestBody AuthInpDto authInpDto,@PathVariable(name = "id") Integer foodItemId,@RequestParam(required = false) String title,@RequestParam(required = false) String description,@RequestParam(required = false) Double price){
        return foodItemService.updateFoodById(authInpDto,foodItemId,title,description,price);
    }
    @DeleteMapping("/food/id/{id}/admin")
    public String deleteFood(@RequestBody AuthInpDto authInpDto,@PathVariable Integer id){
        return foodItemService.deleteFood(authInpDto,id);
    }

}
