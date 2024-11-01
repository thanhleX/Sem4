package test.project4v2.Mediator;

import test.project4v2.command.CreateUserCommand;
import test.project4v2.command.LoginUserCommand;
import test.project4v2.dto.UserDTO;

    public interface Mediator {
        <TResponse> TResponse send(Command<TResponse> command) ;
        <TResponse> TResponse send(Query<TResponse> query);

        void send(CreateUserCommand command);
        void send(LoginUserCommand command);

    interface Command<TResponse> {
    }

    interface Query<TResponse> {
    }
}
