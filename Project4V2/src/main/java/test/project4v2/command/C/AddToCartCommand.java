package test.project4v2.command.C;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;
import test.project4v2.Mediator.Mediator;
import test.project4v2.dto.CartItemDTO;
import test.project4v2.entity.User;
@Getter
@Setter
@Data
@AllArgsConstructor
public class AddToCartCommand implements Mediator.Command<CartItemDTO> {

    private Long productId;
    private String productName;
    private int quantity;
    private double price;

    private String userId;


    public AddToCartCommand(User userId, CartItemDTO cartItem) {
        this.userId = userId.getUsername();
        this.productId = cartItem.getProductId();
        this.productName = cartItem.getProductName();
        this.quantity = cartItem.getQuantity();
        this.price = cartItem.getPrice();
    }
}
