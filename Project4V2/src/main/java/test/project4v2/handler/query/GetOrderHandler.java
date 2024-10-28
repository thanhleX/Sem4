package test.project4v2.handler.query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.project4v2.dto.OrderDTO;
import test.project4v2.entity.Order;
import test.project4v2.handler.QueryHandler;
import test.project4v2.query.GetOrderQuery;
import test.project4v2.repository.OrderRepository;

@Service
public class GetOrderHandler implements QueryHandler<GetOrderQuery, OrderDTO> {
@Autowired
    private OrderRepository orderRepository;
    @Override
        public OrderDTO getHandle(GetOrderQuery query){
            Order order = orderRepository.findById(query.getOrderId()).orElseThrow(() -> new RuntimeException("Order not found"));
            OrderDTO orderDTO = new OrderDTO();
            orderDTO.setOrderId(order.getOrderId());
            orderDTO.setUserId(order.getUserId());
            orderDTO.setOrderDate(order.getOrderDate());
            return orderDTO;
        }

}
