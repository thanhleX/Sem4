package test.project4v2.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {
    private String name;
    private String description;
    private Double price;
    private String imageUrl;
    private Integer stock;
}
