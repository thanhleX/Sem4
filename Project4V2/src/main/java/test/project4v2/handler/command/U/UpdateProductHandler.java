package test.project4v2.handler.command.U;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import test.project4v2.command.U.UpdateProductCommand;
import test.project4v2.dto.ProductDTO;
import test.project4v2.entity.Product;

import test.project4v2.handler.CommandHandler;
import test.project4v2.repository.ProductRepository;

@Component
public class UpdateProductHandler implements CommandHandler<UpdateProductCommand, ProductDTO> {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ProductDTO handle(UpdateProductCommand command) {
        // Retrieve the product by ID
        Product product = productRepository.findById(command.getId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Update product details
        product.setName(command.getName()); // Use command.getName() to get the name
        product.setDescription(command.getDescription());
        product.setPrice(Double.parseDouble(command.getPrice()));

        product.setStockQuantity(command.getStock());

        // Save updated product
        product = productRepository.save(product);

        // Map to ProductDTO
        return mapToDTO(product);
    }

    private ProductDTO mapToDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        productDTO.setPrice(product.getPrice());
        productDTO.setStock(product.getStockQuantity());
        return productDTO;
    }
}