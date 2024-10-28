package org.example.project4.WebPacket.Domain.model.Oders;


import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.project4.WebPacket.Domain.model.Entities;
import org.example.project4.WebPacket.Domain.model.Product;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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