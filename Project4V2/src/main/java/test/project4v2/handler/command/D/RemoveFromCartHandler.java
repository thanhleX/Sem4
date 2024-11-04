package test.project4v2.handler.command.D;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.project4v2.command.D.RemoveFromCartCommand;
import test.project4v2.handler.CommandHandler;
import test.project4v2.service.ShoppingCartService;

@Service
public class RemoveFromCartHandler implements CommandHandler<RemoveFromCartCommand, Void> {
    @Autowired
    private ShoppingCartService shoppingCartService;
    @Override
    public Void handle(RemoveFromCartCommand command) {
        shoppingCartService.removeFromCart(String.valueOf(command.getUserId()),command.getProductId());
        System.out.println("Product deleted successfully.");
        return null;
    }
}
