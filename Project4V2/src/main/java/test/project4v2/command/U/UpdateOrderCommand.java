package test.project4v2.command.U;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import test.project4v2.Mediator.Mediator;
import test.project4v2.dto.OrderDTO;
import test.project4v2.entity.Delivery;
import test.project4v2.entity.Oders.OrderEntity;
import test.project4v2.entity.Oders.OrderItems;
import test.project4v2.entity.Promotion;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateOrderCommand implements Mediator.Command<OrderDTO> {
    private Long id;
    private OrderEntity quantity;
    private Double totalAmount;
    private LocalDateTime updateDate;
    private String shippingAddress;
    private Promotion promotion;
    private Delivery getDeliveryInfo;
    public List<OrderItems> Products;

}
