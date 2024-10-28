
package test.project4v2.entity;

import jakarta.persistence.*;
import lombok.Getter;

import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "OrderItems")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderItemId;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private Integer quantity;
    private Double price;

    public void add(OrderItem orderItem) {
        // Implement logic to add an order item to the order
        orderItem.setOrderItemId(this.orderItemId);
        orderItem.setOrder(this.order);
        orderItem.setProduct(this.product);
        orderItem.setQuantity(this.quantity);
        orderItem.setPrice(this.price);
    }

    // Getters and Setters
}