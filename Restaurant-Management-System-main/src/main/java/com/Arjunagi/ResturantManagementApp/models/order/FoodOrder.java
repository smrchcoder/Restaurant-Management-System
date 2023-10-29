package com.Arjunagi.ResturantManagementApp.models.order;

import com.Arjunagi.ResturantManagementApp.models.FoodItem;
import com.Arjunagi.ResturantManagementApp.models.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class FoodOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToMany
    @JoinTable(name = "order_food_fk_table",
            joinColumns = @JoinColumn(name = "fk_order") ,
            inverseJoinColumns = @JoinColumn(name = "fk_food"))
    private List<FoodItem> foodItemList;
    private Integer quantity;
    @ManyToOne
    @JoinColumn(name = "fkUserId")
    private User user;
    @Enumerated(value = EnumType.STRING)
    private OrderStatus orderStatus;
    private LocalDateTime creationTime;

    public FoodOrder(List<FoodItem> foodItems,User user){
        this.foodItemList=foodItems;
        this.user=user;
        quantity=foodItems.size();
        creationTime=LocalDateTime.now();
        orderStatus=OrderStatus.CREATED;
    }

}
