package test.project4v2.handler.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.project4v2.command.CreateOrderCommand;
import test.project4v2.dto.OrderDTO;
import test.project4v2.entity.Item;
import test.project4v2.entity.Order;
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
        Order order = new Order();
        order.setUserId(command.getUserId());
        order.setOrderDate(command.getOrderDate());
        List<Item> items = IntStream.range(0, command.getProductId().size())
                .mapToObj(i -> {
                    Long productId = command.getProductId().get(i);
                    Integer quantity = command.getQuantities().get(i);

                    Product product = productRepository.findById(productId)
                            .orElseThrow(() -> new RuntimeException("Product not found"));
            Item item = new Item();
            item.setProduct(product);
            item.setQuantity(quantity);
            item.setOrder(order);
            return item;
        }).collect(Collectors.toList());
        order.setItems(items);
        orderRepository.save(order);
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId(order.getOrderId());
        orderDTO.setUserId(order.getUserId());
        orderDTO.setOrderDate(order.getOrderDate());
        return orderDTO;
    }
}