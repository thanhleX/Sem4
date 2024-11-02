package test.project4v2.service;

import org.springframework.stereotype.Service;
import test.project4v2.dto.CartDTO;
import test.project4v2.dto.CartItemDTO;

import java.util.Optional;

@Service
public class ShoppingCartService {
    private final CartDTO cart;

    public ShoppingCartService() {
        this.cart = new CartDTO();
    }


    public void addToCart(CartItemDTO cartItem) {
        Optional<CartItemDTO> existingItem = cart.getItems().stream()
                .filter(item -> item.getProductId().equals(cartItem.getProductId()))
                .findFirst();
        if (existingItem.isPresent()) {
            existingItem.get().setQuantity(existingItem.get().getQuantity() + cartItem.getQuantity());
        } else {
            cart.getItems().add(cartItem);
        }
    }
    public CartDTO getCart() {
        return cart;
    }

    public void clearCart() {
        cart.getItems().clear();
    }

    public void removeFromCart(Long productId) {
        cart.getItems().removeIf(item -> item.getProductId().equals(productId));
    }
}
