package test.project4v2.dto;

import lombok.Getter;
import lombok.Setter;
import test.project4v2.entity.Product;

@Getter
@Setter
public class ProductDTO {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private Integer stock;

    public ProductDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.stock = product.getStockQuantity();
    }

    public ProductDTO() {
    }
}
