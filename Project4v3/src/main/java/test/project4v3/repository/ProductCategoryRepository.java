package test.project4v3.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import test.project4v3.entity.ProductCategory;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {

}