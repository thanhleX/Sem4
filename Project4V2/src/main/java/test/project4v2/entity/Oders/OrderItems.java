package test.project4v2.entity.Oders;


import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import test.project4v2.entity.Entities;
import test.project4v2.entity.Product;

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
    private int quantity;


    public OrderItems(int id, LocalDateTime createDate, LocalDateTime updateDate, Product product, int quantity) {
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

}