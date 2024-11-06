package test.project4v3.entity.Oders;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import test.project4v3.entity.Entities;
import test.project4v3.entity.Product;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class OrderItems extends Entities {
    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @ManyToOne
    @JoinColumn(name = "order_id")  // Foreign key column
    private OrderEntity order;
    private int quantity;


    public OrderItems(Long id, LocalDateTime createDate, LocalDateTime updateDate, Product product, int quantity) {
        super(id, createDate, updateDate);
        if (quantity <= 0) {

            throw new IllegalArgumentException("Quantity must be greater than 0.");
        }
        setId(id);
        setCreateDate(createDate);
        setUpdateDate(updateDate);
        this.product = product;
        this.quantity = quantity;
    }

    public void setId(Long productId) {

    }
}