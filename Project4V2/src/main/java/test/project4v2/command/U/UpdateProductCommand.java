package test.project4v2.command.U;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import test.project4v2.Mediator.Mediator;
import test.project4v2.command.C.CreateUserCommand;
import test.project4v2.dto.ProductDTO;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProductCommand implements Mediator.Command<ProductDTO> {
    private Long id;
    private String name;
    private String description;
    private String price;
    private int stock;

}
