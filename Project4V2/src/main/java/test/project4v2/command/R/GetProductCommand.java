package test.project4v2.command.R;

import lombok.Getter;
import lombok.Setter;
import test.project4v2.Mediator.Mediator;
import test.project4v2.dto.ProductDTO;
@Getter
@Setter
public class GetProductCommand implements Mediator.Command<ProductDTO> {
    private Long productId;
}
