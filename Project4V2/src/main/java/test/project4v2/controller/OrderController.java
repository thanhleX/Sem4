package test.project4v2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import test.project4v2.Mediator.Mediator;
import test.project4v2.command.C.CreateOrderCommand;
import test.project4v2.dto.UserOrderDTO;
import test.project4v2.entity.User;

import test.project4v2.dto.OrderDTO;
import test.project4v2.dto.OrderItemDTO;
import test.project4v2.query.GetOrderQuery;
import test.project4v2.query.GetUserOrdersQuery;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private Mediator mediator;

    // Place a new order
    @PostMapping("/{userId}")
    public ResponseEntity<OrderDTO> placeOrder(@PathVariable User userId, @RequestBody List<OrderItemDTO> orderItems) {
        OrderDTO createdOrder = mediator.send(new CreateOrderCommand(userId, orderItems));
        return ResponseEntity.status(201).body(createdOrder); // Return 201 Created
    }

    // Retrieve order details by ID
    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable Long orderId) {
        OrderDTO order = mediator.send(new GetOrderQuery(orderId));
        if (order == null) {
            return ResponseEntity.notFound().build(); // Return 404 Not Found
        }
        return ResponseEntity.ok(order);
    }

    // List all orders for a user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<UserOrderDTO>> getUserOrders(@PathVariable Long userId) {
        List<UserOrderDTO> orders = mediator.send(new GetUserOrdersQuery(userId));
        return ResponseEntity.ok(orders);
    }
}