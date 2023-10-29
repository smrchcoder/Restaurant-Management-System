package com.Arjunagi.ResturantManagementApp.controllers;

import com.Arjunagi.ResturantManagementApp.models.dto.AuthInpDto;
import com.Arjunagi.ResturantManagementApp.models.order.FoodOrder;
import com.Arjunagi.ResturantManagementApp.models.order.OrderStatus;
import com.Arjunagi.ResturantManagementApp.services.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class OrderController {
    @Autowired
    OrderService orderService;

    @PostMapping("/order") // any register user
    public String placeOrder( @RequestBody AuthInpDto authInpDto, @RequestParam List<Integer> foodIds){
        return orderService.placeOrder(authInpDto,foodIds);
    }

    @PutMapping("/order/{orderId}/status/admin") // the status can be alter by admin
    public String updateOrderStatus( @RequestBody AuthInpDto authInpDto, @PathVariable Integer orderId, @RequestParam OrderStatus orderStatus){
        return orderService.updateOrderStatus(authInpDto,orderId,orderStatus);
    }
    @PutMapping("/order/id/{orderId}") //user can cancel users order admin can cancel any
    public String cancelOrder( @RequestBody AuthInpDto authInpDto,@PathVariable Integer orderId){
        return orderService.cancelOrder(authInpDto,orderId);
    }

    @GetMapping("/orders") // all the orders for the particular user
    public List<FoodOrder> getOrders( @RequestBody AuthInpDto authInpDto){
        return orderService.getOrders(authInpDto);
    }
    @GetMapping("/order/id/{orderId}")//admin can get All user can get those which belongs to user
    public FoodOrder getOrderById(@Valid @RequestBody AuthInpDto authInpDto,@PathVariable Integer orderId){
        return orderService.getOrderById(authInpDto,orderId);
    }

    @DeleteMapping("/order/id/{orderId}/admin")
    public String deleteOrderById( AuthInpDto authInpDto,@PathVariable Integer orderId){
        return orderService.deleteOrderById(authInpDto,orderId);
    }



}
