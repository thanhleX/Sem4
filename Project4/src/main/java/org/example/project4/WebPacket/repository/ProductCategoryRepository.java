package org.example.project4.WebPacket.repository;

import org.example.project4.WebPacket.model.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
}