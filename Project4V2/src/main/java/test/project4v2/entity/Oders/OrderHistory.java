package test.project4v2.entity.Oders;



import test.project4v2.entity.User;

import java.util.ArrayList;
import java.util.List;

public class OrderHistory {
    private final List<OrderEntity> orders;

    public OrderHistory() {
        orders = new ArrayList<>();
    }

    public void addOrder(OrderEntity order) {
        orders.add(order);
    }

    public List<OrderEntity> getOrdersByCustomer(User user) {
        return orders.stream()
                .filter(order -> order.getUser().equals(user))
                .toList();
    }
}
