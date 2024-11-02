package test.project4v2.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.query.Order;
import test.project4v2.entity.User;
@Getter
@Setter
public class UserOrderDTO {
    public User userID;
    public Order orderId;
}
