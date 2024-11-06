package test.project4v3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import test.project4v3.dto.OrderDTO;
import test.project4v3.entity.Oders.OrderEntity;
import test.project4v3.entity.User;
import test.project4v3.service.OrderService;


import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<OrderEntity>> getOrdersByUserId(@PathVariable User userId) {
        List<OrderEntity> orders = orderService.getOrdersByUserId(userId);
        return ResponseEntity.ok(orders);
    }

    @PostMapping
    public ResponseEntity<OrderEntity> createOrder(@RequestBody OrderDTO orderDTO) {
        OrderEntity order = orderService.createOrder(orderDTO);
        return ResponseEntity.ok(order);
    }
}