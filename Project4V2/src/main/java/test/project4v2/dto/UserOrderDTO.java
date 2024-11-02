package test.project4v2.dto;

import lombok.Getter;
import lombok.Setter;

import test.project4v2.entity.Oders.OrderEntity;
import test.project4v2.entity.User;
@Getter
@Setter
public class UserOrderDTO {
    public User userID;
    public OrderEntity orderId;

}
