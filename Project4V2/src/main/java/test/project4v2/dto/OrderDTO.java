package test.project4v2.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import test.project4v2.entity.OrderItem;
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
    private LocalDateTime orderDate;
    private Double totalAmount;
    private String shippingAddress;


    public OrderDTO(Long orderId, LocalDateTime orderDate, List<OrderItem> orderItems, Double totalAmount, String shippingAddress, String status) {
    }
}
