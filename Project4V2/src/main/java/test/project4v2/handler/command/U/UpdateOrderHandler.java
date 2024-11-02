package test.project4v2.handler.command.U;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.project4v2.command.U.UpdateOrderCommand;
import test.project4v2.dto.OrderDTO;
import test.project4v2.entity.Oders.OrderEntity;
import test.project4v2.handler.CommandHandler;
import test.project4v2.repository.OrderRepository;

import java.time.LocalDateTime;

@Service
public class UpdateOrderHandler implements CommandHandler<UpdateOrderCommand, OrderDTO> {
    @Autowired
    private OrderRepository orderRepository;
    @Override
    public OrderDTO handle(UpdateOrderCommand command) {
        OrderEntity order = orderRepository.findById(command.getId()).orElseThrow(() -> new RuntimeException("Order not found"));
        order.setCreateDate(LocalDateTime.now());
        order.setPromotion(command.getPromotion());
        order.setDeliveryInfo(command.getGetDeliveryInfo());
        order.setProducts(command.getProducts());
        orderRepository.save(order);

        return new OrderDTO(
                order.getPromotion(),
                order.getCreateDate(),
                order.getProducts(),
                order.getDeliveryInfo(),
                order.getDeliveryInfo().getShippingStatus()
        );
    }
}
