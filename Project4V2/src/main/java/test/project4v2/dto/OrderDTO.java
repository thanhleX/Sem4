package test.project4v2.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import test.project4v2.entity.Delivery;
import test.project4v2.entity.Oders.OrderItems;
import test.project4v2.entity.Promotion;
import test.project4v2.entity.User;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private Long orderId;
    private User userId;
    private List<OrderItemDTO> orderItems;
    private LocalDateTime createDate;
    private Double totalAmount;
    private String shippingAddress;


    public OrderDTO(Promotion promotion, LocalDateTime createDate, List<OrderItems> products, Delivery deliveryInfo, String shippingStatus) {
    }
}
