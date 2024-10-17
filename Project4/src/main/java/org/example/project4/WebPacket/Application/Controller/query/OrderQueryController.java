package org.example.project4.WebPacket.Application.Controller.query;

import org.example.project4.WebPacket.Domain.model.Order;
import org.example.project4.WebPacket.Infastructure.service.query.OrderQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderQueryController {
        @Autowired
        private OrderQueryService orderQueryService;
        @GetMapping
        public List<Order> getAllOrders() {
            return orderQueryService.getAllOrders();
        }
        @GetMapping("/{id}")
        public Order getOrderById(Long id) {
            return orderQueryService.getOrderById(id);
        }
}

