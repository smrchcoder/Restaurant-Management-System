package com.Arjunagi.ResturantManagementApp.reposotories;

import com.Arjunagi.ResturantManagementApp.models.order.FoodOrder;
import com.Arjunagi.ResturantManagementApp.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOrderRepo extends JpaRepository<FoodOrder,Integer> {
    List<FoodOrder> findByUser(User user);
}
