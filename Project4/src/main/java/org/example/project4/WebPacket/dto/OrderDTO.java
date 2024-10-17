package org.example.project4.WebPacket.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderDTO {
    private Long userId;
    private List<OrderItemDTO> orderItems;
    private String shippingAddress;

    public String getStatus() {
        return "Pending";
    }
}
