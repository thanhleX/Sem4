package test.project4v2.handler.command.U;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.project4v2.command.U.ClearCartCommand;
import test.project4v2.dto.UserDTO;
import test.project4v2.entity.User;
import test.project4v2.handler.CommandHandler;
import test.project4v2.service.ShoppingCartService;
@Service
public class ClearCartCommandHandler implements CommandHandler<ClearCartCommand, UserDTO> {
    @Autowired
    private ShoppingCartService shoppingCartService;
    @Override
    public UserDTO handle(ClearCartCommand command) {
        shoppingCartService.clearCart(String.valueOf(command.getUserId()));
        return new UserDTO(command.getUserId());
    }
}
