package test.project4v2.handler.query.R;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import test.project4v2.dto.ProductDTO;
import test.project4v2.entity.Product;
import test.project4v2.handler.QueryHandler;
import test.project4v2.query.GetAllProductQuery;
import test.project4v2.repository.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GetAllProductQueryHandler implements QueryHandler<GetAllProductQuery, List<ProductDTO>> {
    @Autowired
    private ProductRepository productRepository;


    @Override
    public List<ProductDTO> getHandle(GetAllProductQuery query) {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(ProductDTO::new) // Ensure proper mapping
                .collect(Collectors.toList());
    }
}