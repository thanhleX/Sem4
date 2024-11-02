package test.project4v2.command.C;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import test.project4v2.Mediator.Mediator;
import test.project4v2.dto.OrderDTO;
import test.project4v2.dto.OrderItemDTO;
import test.project4v2.entity.Product;
import test.project4v2.entity.User;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CreateOrderCommand implements Mediator.Command<OrderDTO> {
    @Id
    private User userId;
    private LocalDateTime CreateDate;
    private List<Product> productId;
    private List<Product> quantities;
    private List<OrderItemDTO> orderItems;


    public CreateOrderCommand(User userId, List<OrderItemDTO> orderItems) {
        this.userId = userId;
        this.orderItems = orderItems;
    }
}
