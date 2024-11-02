package test.project4v2.query;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import test.project4v2.Mediator.Mediator.Query;
import test.project4v2.dto.ProductDTO;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetAllProductQuery implements Query<ProductDTO>{
    private Long productId;
}
