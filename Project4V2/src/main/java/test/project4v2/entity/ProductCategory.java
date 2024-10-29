
package test.project4v2.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import test.project4v2.entity.Category;

@Entity
@Table(name = "product_category")
@Getter
@Setter
public class ProductCategory extends Entities {

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;


    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;


    // Getters and Setters
}