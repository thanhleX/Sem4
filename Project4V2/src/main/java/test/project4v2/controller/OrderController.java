package test.project4v2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import test.project4v2.Mediator.Mediator;
import test.project4v2.command.CreateOrderCommand;
import test.project4v2.command.DeleteOrderCommand;
import test.project4v2.command.UpdateOrderCommand;
import test.project4v2.dto.OrderDTO;
import test.project4v2.query.GetOrderQuery;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private Mediator mediator;
    @PostMapping
    public OrderDTO createOrder(@RequestBody CreateOrderCommand command) {
        return mediator.send(command);
    }
    @GetMapping("/{id}")
    public OrderDTO getOrderById(@PathVariable Long id) {
        GetOrderQuery query = new GetOrderQuery(id);
        return mediator.send(query);
    }
    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id) {
        DeleteOrderCommand command = new DeleteOrderCommand(id);
        mediator.send(command);
    }
    @PutMapping("/{id}")
    public OrderDTO updateOrder(@PathVariable Long id, @RequestBody UpdateOrderCommand command) {
        command.setId(id); // Set the ID from the path variable
        return mediator.send(command);
    }
}
