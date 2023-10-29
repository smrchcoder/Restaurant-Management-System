package com.Arjunagi.ResturantManagementApp.models;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Transient;

import java.time.LocalDateTime;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FoodItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String description;
    private LocalDateTime creatingDateTime;
    private String imageUrl;
    private Double price;
    @Transient
    public String getHtmlLink() {
        if (imageUrl != null) {
            return "<a href='" + imageUrl + "'>" + imageUrl + "</a>";
        }
        return "";
    }

    public FoodItem(String title,String description,Double price,String imageUrl){
        this.title=title;
        this.description=description;
        this.imageUrl=imageUrl;
        this.price=price;
        creatingDateTime=LocalDateTime.now();
    }
}
