package test.project4v2.handler.query.R;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import test.project4v2.dto.ProductDTO;
import test.project4v2.entity.Product;
import test.project4v2.repository.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GetAllProductQuery implements test.project4v2.handler.QueryHandler<test.project4v2.query.GetAllProductQuery, List<ProductDTO>> {
    @Autowired
    private ProductRepository productRepository;

    private ProductDTO mapToDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        productDTO.setPrice(product.getPrice());
        productDTO.setStock(product.getStockQuantity());
        return productDTO;
    }

    @Override
    public List<ProductDTO> getHandle(test.project4v2.query.GetAllProductQuery query) {
        List<Product> products = productRepository.findAll();

        // Map Product entities to ProductDTOs
        return products.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
}
