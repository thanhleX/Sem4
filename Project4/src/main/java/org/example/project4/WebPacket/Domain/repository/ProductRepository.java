package org.example.project4.WebPacket.Domain.repository;

import org.example.project4.WebPacket.Domain.model.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategory(String category);

    List<Product> findByPriceBetween(double minPrice, double maxPrice);


    @Query("SELECT p FROM Product p JOIN ProductCategory pc ON p.productId = pc.product.productId WHERE pc.category.categoryId = ?1")
    List<Product> findByCategoryId(Long categoryId);
}