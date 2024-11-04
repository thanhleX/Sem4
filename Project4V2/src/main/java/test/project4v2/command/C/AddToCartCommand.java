package test.project4v2.command.C;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import test.project4v2.Mediator.Mediator;
import test.project4v2.dto.CartItemDTO;
import test.project4v2.entity.User;

@Data
@AllArgsConstructor
public class AddToCartCommand implements Mediator.Command<CartItemDTO> {

    private Long productId;
    private String productName;
    private int quantity;
    private double price;
    @Getter
    private String userId;



}
