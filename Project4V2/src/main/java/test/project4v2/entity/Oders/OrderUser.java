package test.project4v2.entity.Oders;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import test.project4v2.entity.Entities;
import test.project4v2.entity.User;

@Entity
@Table(name = "order_user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderUser extends Entities {
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private OrderEntity orderId;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User userId;
}
