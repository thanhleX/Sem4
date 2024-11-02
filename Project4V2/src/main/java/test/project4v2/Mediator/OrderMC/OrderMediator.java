package test.project4v2.Mediator.OrderMC;

import test.project4v2.dto.OrderDTO;
import test.project4v2.dto.OrderItemDTO;
import test.project4v2.entity.User;

import java.util.List;

public interface OrderMediator {
    OrderDTO placeOrder(User userId, List<OrderItemDTO> orderItems);
    OrderDTO getOrderById(Long orderId);
    List<OrderDTO> getUserOrders(User userId);
}
