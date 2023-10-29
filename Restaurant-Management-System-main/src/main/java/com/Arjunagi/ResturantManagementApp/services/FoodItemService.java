package com.Arjunagi.ResturantManagementApp.services;

import com.Arjunagi.ResturantManagementApp.models.FoodItem;
import com.Arjunagi.ResturantManagementApp.models.UserAuthToken;
import com.Arjunagi.ResturantManagementApp.models.dto.AuthInpDto;
import com.Arjunagi.ResturantManagementApp.models.dto.FoodDto;
import com.Arjunagi.ResturantManagementApp.models.user.Role;
import com.Arjunagi.ResturantManagementApp.reposotories.IFoodItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

@Service
public class FoodItemService {
    @Autowired
    IFoodItemRepo foodItemRepo;
    @Autowired
    UserAuthTokenService userAuthTokenService;
    @Autowired
    UserService userService;
    private boolean isNotAValidUser(AuthInpDto authInpDto){
        UserAuthToken userAuthToken=userAuthTokenService.getUserAuthToken(authInpDto);
        if(userAuthToken==null)return true;
       return !userAuthToken.getUser().getRole().equals(Role.ADMIN);
    }

    public String addFood(FoodDto foodDto) {
        if(isNotAValidUser(foodDto.getAuthInpDto()))return "don't have privileges";
        FoodItem foodItem=new FoodItem(foodDto.getTitle(),foodDto.getDescription(),foodDto.getPrice(),foodDto.getImageUrl());
        foodItemRepo.save(foodItem);
        return "added sucessfully";
    }

    public List<FoodItem> getAll() {
        return foodItemRepo.findAll();
    }


    private boolean isNullOrEmpty(String s){
        return s!=null&&!s.isEmpty();
    }

    private void updateIfValid(String s, Predicate<String> valid, Consumer<String> apply){
        if(valid.test(s)){
            apply.accept(s);
        }
    }

    public String updateFoodById(AuthInpDto authInpDto, Integer foodItemId, String title, String description, Double price) {
        if(isNullOrEmpty(title)&&isNullOrEmpty(description)&&price==null) return "nothing to update";
        if(isNotAValidUser(authInpDto))return "don't have privileges";
        FoodItem foodItem=foodItemRepo.findById(foodItemId).orElseThrow();
        if(price!=null)foodItem.setPrice(price);
        updateIfValid(title,this::isNullOrEmpty,foodItem::setTitle);
        updateIfValid(description,this::isNullOrEmpty,foodItem::setDescription);
        foodItemRepo.save(foodItem);
        return "updated sucessfully";
    }

    public List<FoodItem> findByIds(List<Integer> foodIds) {
        return foodItemRepo.findAllById(foodIds);
    }

    public String deleteFood(AuthInpDto authInpDto, Integer id) {
        if(userService.isAdmin(authInpDto)){
            foodItemRepo.deleteById(id);
            return "deleted sucessfully";
        }
        return "user don't have authorization";
    }
}
