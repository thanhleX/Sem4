package test.project4v2.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemDTO {
    private Long id;
    private Long productId;
    private int quantity;
    private double price;
    private String name;
    private String description;
    private int stock;
}
