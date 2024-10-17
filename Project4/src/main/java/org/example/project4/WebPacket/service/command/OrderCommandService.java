package org.example.project4.WebPacket.service.command;

import org.example.project4.WebPacket.dto.OrderDTO;
import org.example.project4.WebPacket.dto.OrderItemDTO;
import org.example.project4.WebPacket.model.Order;
import org.example.project4.WebPacket.model.OrderItem;
import org.example.project4.WebPacket.model.User;
import org.example.project4.WebPacket.repository.OrderRepository;
import org.example.project4.WebPacket.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderCommandService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductRepository productRepository;

    public void createOrder(OrderDTO orderDTO) {
        Order order = new Order();
        order.setUser(new User(orderDTO.getUserId()));
        order.setShippingAddress(orderDTO.getShippingAddress());
        double totalAmount = 0;
        for (OrderItemDTO itemDTO : orderDTO.getOrderItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(productRepository.findById(itemDTO.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found")));
            orderItem.setQuantity(itemDTO.getQuantity());
            orderItem.setPrice(orderItem.getProduct().getPrice() * itemDTO.getQuantity());
            totalAmount += orderItem.getPrice();
            order.addOrderItem(orderItem);
        }
        order.setTotalAmount(totalAmount);
        order.setStatus("Pending");
        orderRepository.save(order);
    }

    public void updateOrder(Long id, OrderDTO orderDTO) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
        if (order != null) {
            order.setShippingAddress(orderDTO.getShippingAddress());
            order.setStatus(orderDTO.getStatus());
            orderRepository.save(order);
        }
    }
}
