package test.project4v2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import test.project4v2.dto.CartDTO;
import test.project4v2.dto.CartItemDTO;
import test.project4v2.service.ShoppingCartService;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final ShoppingCartService shoppingCartService;

    @Autowired
    public CartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    // Add item to cart
    @PostMapping("/{userId}/items")
    public ResponseEntity<Void> addItemToCart(@PathVariable String userId, @RequestBody CartItemDTO cartItem) {
        shoppingCartService.addToCart(userId, cartItem);
        return ResponseEntity.status(201).build(); // Return 201 Created
    }

    // Remove item from cart
    @DeleteMapping("/{userId}/items/{productId}")
    public ResponseEntity<Void> removeItemFromCart(@PathVariable String userId, @PathVariable Long productId) {
        shoppingCartService.removeFromCart(userId, productId);
        return ResponseEntity.noContent().build(); // Return 204 No Content
    }

    // Clear the cart
    @DeleteMapping("/{userId}/clear")
    public ResponseEntity<Void> clearCart(@PathVariable String userId) {
        shoppingCartService.clearCart(userId);
        return ResponseEntity.noContent().build(); // Return 204 No Content
    }

    // Get the user's cart
    @GetMapping("/{userId}")
    public ResponseEntity<CartDTO> getCart(@PathVariable String userId) {
        CartDTO cart = shoppingCartService.getCart(userId);
        return ResponseEntity.ok(cart); // Return 200 OK
    }
}