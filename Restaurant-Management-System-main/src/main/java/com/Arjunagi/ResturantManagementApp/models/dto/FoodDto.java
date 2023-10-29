package com.Arjunagi.ResturantManagementApp.models.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FoodDto {
    @NotBlank
    private String title;
    @NotBlank
    private String description;
    private Double price;
    private String imageUrl;
    private AuthInpDto authInpDto;
}
