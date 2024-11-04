package test.project4v2.Mediator;

import test.project4v2.command.C.CreateUserCommand;
import test.project4v2.command.R.LoginUserCommand;
import test.project4v2.dto.CartItemDTO;
import test.project4v2.dto.ProductDTO;

import java.util.List;

public interface Mediator {
        <TResponse> TResponse send(Command<TResponse> command) ;
        <TResponse> TResponse send(Query<TResponse> query);

        List<ProductDTO> send(CreateUserCommand command);
        void send(LoginUserCommand command);

    interface Command<T> {
    }

    interface Query<TResponse> {
    }
}
