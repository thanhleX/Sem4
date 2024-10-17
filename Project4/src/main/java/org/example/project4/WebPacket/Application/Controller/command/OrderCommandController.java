package org.example.project4.WebPacket.Application.Controller.command;

import org.example.project4.WebPacket.Domain.dto.OrderDTO;
import org.example.project4.WebPacket.Domain.model.Order;
import org.example.project4.WebPacket.Infastructure.service.command.OrderCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/orders")
public class OrderCommandController {
@Autowired
    private OrderCommandService orderCommandService;
    @PostMapping
    public CompletableFuture<ResponseEntity<Order>> createOrder(@RequestBody OrderDTO orderDTO) {
        return orderCommandService.createOrder(orderDTO)
                .thenApply(order -> ResponseEntity.status(HttpStatus.CREATED).body(order))
                .exceptionally(ex -> ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null));
    }

    @PutMapping("/{id}")
    public CompletableFuture<ResponseEntity<Order>> updateOrder(
            @PathVariable Long id, @RequestBody OrderDTO orderDTO) {
        return orderCommandService.updateOrder(id, orderDTO)
                .thenApply(ResponseEntity::ok)
                .exceptionally(ex -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

}
