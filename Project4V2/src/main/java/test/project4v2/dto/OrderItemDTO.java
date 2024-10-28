package test.project4v2.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemDTO {
    private Long productId;
    private String productName;
    private Integer quantity;
}
