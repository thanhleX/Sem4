package test.project4v2.Mediator.OrderMC;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import test.project4v2.dto.OrderDTO;
import test.project4v2.dto.OrderItemDTO;
import test.project4v2.entity.User;
import test.project4v2.service.OrderService;

import java.util.List;

@Component
public class OrderMediatorImpl implements OrderMediator {

    @Autowired
    private OrderService orderService;

    @Override
    public OrderDTO placeOrder(User userId, List<OrderItemDTO> orderItems) {
        return orderService.placeOrder(userId, orderItems);
    }

    @Override
    public OrderDTO getOrderById(Long orderId) {
        return orderService.getOrderById(orderId);
    }

    @Override
    public List<OrderDTO> getUserOrders(User userId) {
        return orderService.getUserOrders(userId);
    }
}