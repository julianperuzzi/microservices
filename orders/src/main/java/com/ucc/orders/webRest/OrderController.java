package com.ucc.orders.webRest;


import com.ucc.orders.model.entities.Order;
import com.ucc.orders.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor

public class OrderController {

    private final OrderService orderService;

    @GetMapping("api/orders")
    @ResponseStatus(HttpStatus.OK)
    public List<Order> getOrders(){
        return orderService.getOrders();
    }

    @PostMapping("api/orders")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Order>newOrders( @RequestBody Order order){
        return (ResponseEntity<Order>) orderService.newOrder(order);
    }

    @DeleteMapping("/api/orders/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Order> deleteProduct(@PathVariable Long id){
        return orderService.deleteOrder(id);
    }



}
