package test.project4v2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import test.project4v2.Mediator.Mediator;

import test.project4v2.command.C.AddToCartCommand;
import test.project4v2.command.D.RemoveFromCartCommand;
import test.project4v2.command.U.ClearCartCommand;
import test.project4v2.dto.CartDTO;
import test.project4v2.dto.CartItemDTO;
import test.project4v2.entity.User;
import test.project4v2.query.GetCartQuery;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final Mediator mediator;

    @Autowired
    public CartController(Mediator mediator) {
        this.mediator = mediator;
    }

    // Add item to cart
    @PostMapping("/{userId}/items")
    public ResponseEntity<Void> addItemToCart(@PathVariable User userId, @RequestBody CartItemDTO cartItem) {
        mediator.send(new AddToCartCommand(userId, cartItem));
        return ResponseEntity.status(201).build(); // Return 201 Created
    }

    // Remove item from cart
    @DeleteMapping("/{userId}/items/{productId}")
    public ResponseEntity<Void> removeItemFromCart(@PathVariable User userId, @PathVariable Long productId) {
        mediator.send(new RemoveFromCartCommand(userId, productId));
        return ResponseEntity.noContent().build(); // Return 204 No Content
    }

    // Clear the cart
    @DeleteMapping("/{userId}/clear")
    public ResponseEntity<Void> clearCart(@PathVariable User userId) {
        mediator.send(new ClearCartCommand(userId));
        return ResponseEntity.noContent().build(); // Return 204 No Content
    }

    // Get the user's cart
    @GetMapping("/{userId}")
    public ResponseEntity<CartDTO> getCart(@PathVariable User userId) {
        CartDTO cart = mediator.send(new GetCartQuery(userId));
        return ResponseEntity.ok(cart); // Return 200 OK
    }
}