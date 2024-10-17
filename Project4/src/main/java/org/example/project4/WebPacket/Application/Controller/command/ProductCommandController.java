package org.example.project4.WebPacket.Application.Controller.command;

import org.example.project4.WebPacket.Domain.dto.ProductDTO;
import org.example.project4.WebPacket.Infastructure.service.command.ProductCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/products")
public class ProductCommandController {
    @Autowired
    private ProductCommandService productCommandService;
    @PostMapping
    public CompletableFuture<ProductDTO> createProduct(@RequestBody ProductDTO productDTO) {
        return productCommandService.createProduct(productDTO);
    }

    @PostMapping("/{id}")
    public CompletableFuture<Void> updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
        return CompletableFuture.runAsync(() -> productCommandService.updateProduct(id, productDTO));
    }

    @PostMapping("/{id}")
    public CompletableFuture<Void> deleteProduct(@PathVariable Long id) {
        return CompletableFuture.runAsync(() -> productCommandService.deleteProduct(id));
    }

}
