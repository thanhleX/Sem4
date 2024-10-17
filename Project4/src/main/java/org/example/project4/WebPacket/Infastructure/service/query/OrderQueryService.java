package org.example.project4.WebPacket.Infastructure.service.query;

import org.example.project4.WebPacket.Domain.model.Order;
import org.example.project4.WebPacket.Domain.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderQueryService {

    @Autowired
    private OrderRepository orderRepository;

    /**
     * Retrieve all orders.
     *
     * @return List of all orders
     */
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    /**
     * Retrieve an order by its ID.
     *
     * @param id Order ID
     * @return Order with the specified ID
     */
    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }
}