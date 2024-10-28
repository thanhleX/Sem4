package org.example.project4.WebPacket.Domain.model.Oders;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.project4.WebPacket.Domain.model.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class OrderEntity extends Entities {

    @OneToOne
    @JoinColumn(name = "customer_id")
    private User user;

    @OneToMany(mappedBy = "order")  // Adjust this if OrderItems has a reference to OrderEntity
    private List<OrderItems> products;

    @OneToOne
    @JoinColumn(name = "delivery_info_id")
    private Delivery deliveryInfo;

    @OneToOne
    @JoinColumn(name = "promotion_id")
    private Promotion promotion;

    public OrderEntity(int id, LocalDateTime createDate, LocalDateTime updateDate, User user,
                       List<OrderItems> products, Delivery deliveryInfo, Promotion promotion) {
        super(id, createDate, updateDate);
        this.user = user;
        this.products = products;
        this.deliveryInfo = deliveryInfo;
        this.promotion = promotion;
    }
    public double calculateTotal() {
        double total = products.stream()
                .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();
        if (promotion != null) {
            total = promotion.applyDiscount(total);
        }
        return total;
    }
}