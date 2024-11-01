package test.project4v2.Mediator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import test.project4v2.command.CreateUserCommand;
import test.project4v2.command.LoginUserCommand;
import test.project4v2.dto.UserDTO;
import test.project4v2.entity.User;
import test.project4v2.handler.CommandHandler;
import test.project4v2.handler.QueryHandler;
import test.project4v2.repository.UserRepository;

@Component
public class MediatorImpl implements Mediator {
    private final UserRepository userRepository;
    private final ApplicationContext applicationContext;


    @Autowired
    public MediatorImpl(UserRepository userRepository, ApplicationContext applicationContext) {
        this.userRepository = userRepository;
        this.applicationContext = applicationContext;
    }

    @Override
    public <TResponse> TResponse send(Command<TResponse> command)  {
        var handler = applicationContext.getBean(getHandlerClass(command));
        return ((CommandHandler<Command<TResponse>, TResponse>) handler).handle(command);
    }

    @Override
    public <TResponse> TResponse send(Query<TResponse> query) {
        var handler = applicationContext.getBean(getHandlerClass(query));
        return ((QueryHandler<Query<TResponse>, TResponse>) handler).getHandle(query);
    }



    @Override
    public void send(CreateUserCommand command) {
        UserDTO userDTO = command.getUserDTO();
        User user = new User(userDTO.getUsername(), userDTO.getPassword(), userDTO.getEmail(), userDTO.getPhone());
        userRepository.save(user);
    }

    @Override
    public void send(LoginUserCommand command) {
        String username = command.getUsername();
        String password = command.getPassword();
        User user = userRepository.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {

        } else {
            // Authentication failed
        }
    }


    private Class<?> getHandlerClass(Object request) {
        if (request instanceof Command<?>) {
            return getCommandHandlerClass(request);
        } else if (request instanceof Query) {
            return getQueryHandlerClass(request);
        }
        throw new IllegalArgumentException("Unknown request type: " + request.getClass());
    }

    private Class<?> getCommandHandlerClass(Object command) {
        String handlerName = command.getClass().getSimpleName() + "Handler";
        try {
            return Class.forName("com.example.yourapp.command.handler." + handlerName);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Handler not found for command: " + command.getClass(), e);
        }
    }

    private Class<?> getQueryHandlerClass(Object query) {
        String handlerName = query.getClass().getSimpleName() + "Handler";
        try {
            return Class.forName("com.example.yourapp.query.handler." + handlerName);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Handler not found for query: " + query.getClass(), e);
        }
    }
}
