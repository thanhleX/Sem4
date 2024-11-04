package test.project4v2.handler.query.R;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import test.project4v2.dto.OrderDTO;
import test.project4v2.entity.Oders.OrderEntity;
import test.project4v2.handler.QueryHandler;
import test.project4v2.repository.OrderRepository;
@Component
public class GetOrderQuery implements QueryHandler<test.project4v2.query.GetOrderQuery, OrderDTO> {
@Autowired
    private OrderRepository orderRepository;
    @Override
        public OrderDTO getHandle(test.project4v2.query.GetOrderQuery query){
            OrderEntity order = orderRepository.findById(query.getOrderId()).orElseThrow(() -> new RuntimeException("Order not found"));
            OrderDTO orderDTO = new OrderDTO(order.getPromotion(), order.getCreateDate(), order.getProducts(), order.getDeliveryInfo(), order.getDeliveryInfo().getShippingStatus());
            orderDTO.setOrderId(order.getId());
            orderDTO.setUserId(order.getUserId());
            orderDTO.setCreateDate(order.getCreateDate());
            return orderDTO;
        }

}
