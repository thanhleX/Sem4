package org.example.project4.WebPacket.Infastructure.service.command;

import org.example.project4.WebPacket.Domain.dto.OrderDTO;
import org.example.project4.WebPacket.Domain.dto.OrderItemDTO;
import org.example.project4.WebPacket.Domain.model.Order;
import org.example.project4.WebPacket.Domain.model.OrderItem;
import org.example.project4.WebPacket.Domain.model.User;
import org.example.project4.WebPacket.Domain.repository.OrderRepository;
import org.example.project4.WebPacket.Domain.repository.ProductRepository;
import org.example.project4.WebPacket.Domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;


@Service
public class OrderCommandService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;
    @Async
    public CompletableFuture<Order> createOrder(OrderDTO orderDTO) {
        User user = userRepository.findById(orderDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Order order = new Order();
        order.setUser(user);
        order.setShippingAddress(orderDTO.getShippingAddress());

        // Process order items
        for (OrderItemDTO itemDTO : orderDTO.getOrderItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(productRepository.findById(itemDTO.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found")));
            orderItem.setQuantity(itemDTO.getQuantity());
            orderItem.setOrder(order);
            order.getOrderItems().add(orderItem);
        }
        orderRepository.save(order);
        return CompletableFuture.completedFuture(order);
    }

    @Async
    public CompletableFuture<Order> updateOrder(Long id, OrderDTO orderDTO) {
        Order existingOrder = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        existingOrder.setShippingAddress(orderDTO.getShippingAddress());
        existingOrder.getOrderItems().clear(); // Clear existing items

        // Process new order items
        for (OrderItemDTO itemDTO : orderDTO.getOrderItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(productRepository.findById(itemDTO.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found")));
            orderItem.setQuantity(itemDTO.getQuantity());
            orderItem.setOrder(existingOrder);
            existingOrder.getOrderItems().add(orderItem);
        }
        orderRepository.save(existingOrder);
        return CompletableFuture.completedFuture(existingOrder);
    }
}
