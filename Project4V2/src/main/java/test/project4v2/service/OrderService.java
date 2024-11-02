package test.project4v2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.project4v2.dto.OrderDTO;
import test.project4v2.dto.OrderItemDTO;

import test.project4v2.entity.Oders.OrderEntity;
import test.project4v2.entity.Oders.OrderItems;
import test.project4v2.entity.User;
import test.project4v2.repository.OrderRepository;

import java.util.Collections;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    // Place a new order
    public OrderDTO placeOrder(User userId, List<OrderItemDTO> orderItems) {
        OrderEntity order = new OrderEntity();
        order.setUserId(userId); // Assuming you have a field for user ID
        order.calculateTotal(); // Calculate the total based on the order items

        // Create order items from the provided list
        for (OrderItemDTO itemDTO : orderItems) {
            OrderItems orderItem = new OrderItems();
            orderItem.setId(itemDTO.getProductId());
            orderItem.setQuantity(itemDTO.getQuantity());
            orderItem.setOrder(order); // Link to the order
            order.getProducts().add(orderItem);
        }

        orderRepository.save(order);
        return mapToDTO(order);
    }

    // Retrieve order details by ID
    public OrderDTO getOrderById(Long orderId) {
        OrderEntity order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        return mapToDTO(order);
    }

    // List all orders for a user
    public List<OrderDTO> getUserOrders(User userId) {
        List<OrderEntity> orders = Collections.singletonList(orderRepository.findByUserId(userId));
        return orders.stream().map(this::mapToDTO).toList();
    }



    // Helper method to map Order to OrderDTO
    private OrderDTO mapToDTO(OrderEntity order) {
        OrderDTO dto = new OrderDTO();
        dto.setOrderId(order.getId());
        dto.setTotalAmount(order.getTotalAmount());

        return dto;
    }
}