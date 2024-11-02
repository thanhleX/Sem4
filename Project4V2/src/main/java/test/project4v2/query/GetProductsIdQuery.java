package test.project4v2.query;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import test.project4v2.dto.ProductDTO;
import test.project4v2.Mediator.Mediator.Query;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetProductsIdQuery implements Query<ProductDTO> {
    private Long productId;
}
