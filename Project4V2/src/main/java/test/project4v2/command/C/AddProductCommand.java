package test.project4v2.command.C;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import test.project4v2.Mediator.Mediator;
import test.project4v2.dto.ProductDTO;


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






}
