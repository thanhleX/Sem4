package test.project4v2.handler.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import test.project4v2.command.UpdateOrderCommand;
import test.project4v2.dto.OrderDTO;
import test.project4v2.entity.Order;
import test.project4v2.handler.CommandHandler;
import test.project4v2.repository.OrderRepository;
@Service
public class UpdateOrderHandler implements CommandHandler<UpdateOrderCommand, OrderDTO> {
    @Autowired
    private OrderRepository orderRepository;
    @Override
    public OrderDTO handle(UpdateOrderCommand command) {
        Order order = orderRepository.findById(command.getId()).orElseThrow(() -> new RuntimeException("Order not found"));
        order.setOrderDate(command.getOrderDate());
        order.setOrderItems(command.getProductName());
        order.setTotalAmount(command.getTotalAmount());
        order.setShippingAddress(command.getShippingAddress());
        order.setStatus(command.getStatus());
        orderRepository.save(order);
        return new OrderDTO(order.getOrderId(), order.getOrderDate(), order.getOrderItems(), order.getTotalAmount(), order.getShippingAddress(), order.getStatus());
    }
}
