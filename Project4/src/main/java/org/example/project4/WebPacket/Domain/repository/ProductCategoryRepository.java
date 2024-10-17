package org.example.project4.WebPacket.Domain.repository;

import org.example.project4.WebPacket.Domain.model.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
}