package org.example.project4.WebPacket.Application.Controller.query;

import org.example.project4.WebPacket.Domain.model.Product;
import org.example.project4.WebPacket.Infastructure.service.query.ProductQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductQueryController {
    @Autowired
    private ProductQueryService productQueryService;
    @GetMapping
    public List<Product> getAllProducts() {
        return productQueryService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Optional<Product> getProductById(Long id) {
        return productQueryService.getProductById(id);
    }

    @GetMapping("/category/{category}")
    public List<Product> getProductsByCategory(String category) {
        return productQueryService.getProductsByCategory(category);
    }

    @GetMapping("/price-range")
    public List<Product> getProductsByPriceRange(double minPrice, double maxPrice) {
        return productQueryService.getProductsByPriceRange(minPrice, maxPrice);
    }

    @GetMapping("/category/{categoryId}")
    public List<Product> getProductsByCategoryId(Long categoryId) {
        return productQueryService.getProductsByCategoryId(categoryId);
    }
}
