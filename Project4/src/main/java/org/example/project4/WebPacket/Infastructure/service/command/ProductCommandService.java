package org.example.project4.WebPacket.Infastructure.service.command;

import org.example.project4.WebPacket.Domain.dto.ProductDTO;
import org.example.project4.WebPacket.Domain.model.Product;
import org.example.project4.WebPacket.Domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class ProductCommandService {
    @Autowired
    private ProductRepository productRepository;
    @Async
    public CompletableFuture<ProductDTO> createProduct(ProductDTO productDTO) {
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setStock(productDTO.getStock());
        product.setImageUrl(productDTO.getImageUrl());
        productRepository.save(product);
        return null;
    }
    @Async
    public CompletableFuture<Void> updateProduct(Long id, ProductDTO productDTO) {
        return CompletableFuture.runAsync(() -> {
            Product product = productRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            product.setName(productDTO.getName());
            product.setDescription(productDTO.getDescription());
            product.setPrice(productDTO.getPrice());
            product.setStock(productDTO.getStock());
            product.setImageUrl(productDTO.getImageUrl());

            productRepository.save(product);
        });
    }
    @Async
    public CompletableFuture<Void> deleteProduct(Long productId) {
        return CompletableFuture.runAsync(() -> {
            productRepository.deleteById(productId);
        });
    }
}
