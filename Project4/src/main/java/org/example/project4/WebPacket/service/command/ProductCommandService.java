package org.example.project4.WebPacket.service.command;

import lombok.Setter;
import org.example.project4.WebPacket.dto.ProductDTO;
import org.example.project4.WebPacket.model.Product;
import org.example.project4.WebPacket.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductCommandService {
    @Autowired
    private ProductRepository productRepository;

    public void createProduct(ProductDTO productDTO) {
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setStock(productDTO.getStock());
        product.setImageUrl(productDTO.getImageUrl());
        productRepository.save(product);
    }
    public void updateProduct(Long id, ProductDTO productDTO) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        if (product != null) {
            product.setName(productDTO.getName());
            product.setDescription(productDTO.getDescription());
            product.setPrice(productDTO.getPrice());
            product.setStock(productDTO.getStock());
            product.setImageUrl(productDTO.getImageUrl());
            productRepository.save(product);
        }
    }

    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }
}
