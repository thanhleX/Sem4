package test.project4v2.handler.command.C;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.project4v2.command.C.CreateOrderCommand;
import test.project4v2.dto.OrderDTO;
import test.project4v2.entity.Oders.OrderEntity;
import test.project4v2.entity.Oders.OrderItems;
import test.project4v2.entity.Product;
import test.project4v2.handler.CommandHandler;
import test.project4v2.repository.OrderRepository;
import test.project4v2.repository.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@Service
public class CreateOrderHandler implements CommandHandler<CreateOrderCommand, OrderDTO> {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductRepository productRepository;
@Override
public OrderDTO handle(CreateOrderCommand command) {
    OrderEntity order = new OrderEntity();
    order.setUserId(command.getUserId());
    order.setCreateDate(command.getCreateDate());
    List<OrderItems> orderItemsList = IntStream.range(0, command.getProductId().size())
            .mapToObj(i -> {
                Long productId = command.getProductId().get(i);
                Integer quantity = command.getQuantities().get(i);

                Product product = productRepository.findById(productId)
                        .orElseThrow(() -> new RuntimeException("Product not found"));
                OrderItems orderItem = new OrderItems();
                orderItem.setProduct(product);
                orderItem.setQuantity(quantity);
                orderItem.setCreateDate(command.getCreateDate());
                return orderItem;
            }).collect(Collectors.toList());
    order.setProducts(orderItemsList);
    orderRepository.save(order);
    OrderDTO orderDTO = new OrderDTO(order.getPromotion(), order.getCreateDate(), order.getProducts(), order.getDeliveryInfo(), order.getDeliveryInfo().getShippingStatus());
    orderDTO.setOrderId(order.getId());
    orderDTO.setUserId(order.getUserId());
    orderDTO.setCreateDate(order.getCreateDate());
    return orderDTO;
}
}