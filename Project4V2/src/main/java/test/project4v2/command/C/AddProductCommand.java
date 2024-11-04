package test.project4v2.command.C;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import test.project4v2.Mediator.Mediator;
import test.project4v2.dto.ProductDTO;
import test.project4v2.service.ProductService;

@Getter
@Setter
@AllArgsConstructor
public class AddProductCommand implements Mediator.Command<ProductDTO> {
    private ProductDTO productDTO;
    private Long id;
    private String name;
    private String description;
    private Double price;
    private Integer stock;


    private final ProductService productService; // Injecting the service

    public AddProductCommand(ProductDTO productDTO, ProductService productService) {
        this.productDTO = productDTO;
        this.productService = productService; // Initialize the service
        this.name = productDTO.getName();
        this.description = productDTO.getDescription();
        this.price = productDTO.getPrice();
        this.stock = productDTO.getStock();
    }

}
