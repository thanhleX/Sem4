package test.project4v3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import test.project4v3.dto.CartDTO;
import test.project4v3.dto.CartItemDTO;
import test.project4v3.entity.User;
import test.project4v3.service.ShoppingCartService;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final ShoppingCartService cartService;

    @Autowired
    public CartController(ShoppingCartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<CartDTO> getCart(@PathVariable User userId) {
        CartDTO cartDTO = cartService.getCart(userId);
        return ResponseEntity.ok(cartDTO);
    }

    @PostMapping("/{userId}/add")
    public ResponseEntity<Void> addToCart(@PathVariable User userId, @RequestBody CartItemDTO itemDTO) {
        cartService.addToCart(userId, itemDTO);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{userId}/remove")
    public ResponseEntity<Void> removeFromCart(@PathVariable User userId, @RequestParam Long productId) {
        cartService.removeFromCart(String.valueOf(userId), productId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{userId}/clear")
    public ResponseEntity<Void> clearCart(@PathVariable User userId) {
        cartService.clearCart(String.valueOf(userId));
        return ResponseEntity.ok().build();
    }
}