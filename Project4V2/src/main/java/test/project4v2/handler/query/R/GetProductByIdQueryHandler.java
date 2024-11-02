package test.project4v2.handler.query.R;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import test.project4v2.dto.ProductDTO;
import test.project4v2.entity.Product;
import test.project4v2.handler.QueryHandler;
import test.project4v2.query.GetProductsIdQuery;

import test.project4v2.repository.ProductRepository;

@Component
public class GetProductByIdQueryHandler implements QueryHandler<GetProductsIdQuery, ProductDTO> {

    @Autowired
    private  ProductRepository productRepository;

    @Override
    public ProductDTO getHandle(GetProductsIdQuery query) {
        Product product = productRepository.findById(query.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Here you would typically map the Product entity to a ProductDTO
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        productDTO.setPrice(product.getPrice());
        productDTO.setStock(product.getStockQuantity());

        return productDTO;
    }
}
