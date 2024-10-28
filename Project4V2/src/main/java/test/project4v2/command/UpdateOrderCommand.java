package test.project4v2.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import test.project4v2.Mediator.Mediator;
import test.project4v2.dto.OrderDTO;
import test.project4v2.entity.OrderItem;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateOrderCommand implements Mediator.Command<OrderDTO> {
    private Long id;
    private List<OrderItem> productName;
    private Integer quantity;
    private String status;
    private Double totalAmount;
    private LocalDateTime orderDate;
    private String shippingAddress;



}
