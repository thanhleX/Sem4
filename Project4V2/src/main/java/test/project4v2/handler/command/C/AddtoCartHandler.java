package test.project4v2.handler.command.C;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import test.project4v2.command.C.AddToCartCommand;
import test.project4v2.dto.CartItemDTO;
import test.project4v2.handler.CommandHandler;
import test.project4v2.service.ShoppingCartService;

@Service
public class AddtoCartHandler implements CommandHandler<AddToCartCommand, CartItemDTO> {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Override
    public CartItemDTO handle(AddToCartCommand command) {
        // Create a CartItemDTO from the command data
        CartItemDTO cartItem = new CartItemDTO(
                command.getProductId(),
                command.getProductName(),
                command.getQuantity(),
                command.getPrice()
        );

        // Use the shoppingCartService to add the item to the cart
        shoppingCartService.addToCart(command.getUserId(),cartItem);

        // Return the added CartItemDTO
        return cartItem;
    }
}