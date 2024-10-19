package test.project4v2.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import test.project4v2.entity.ProductCategory;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
}