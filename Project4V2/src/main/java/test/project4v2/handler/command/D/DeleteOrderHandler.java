package test.project4v2.handler.command.D;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.project4v2.command.D.DeleteOrderCommand;
import test.project4v2.handler.CommandHandler;
import test.project4v2.repository.OrderRepository;
@Service
public class DeleteOrderHandler implements CommandHandler<DeleteOrderCommand, Void> {
    @Autowired
    private OrderRepository orderRepository;
    @Override
    public Void handle(DeleteOrderCommand command) {
        orderRepository.deleteById(command.getOrderId());
        return null;
    }
}
