package test.project4v2.Mediator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import test.project4v2.command.C.CreateUserCommand;
import test.project4v2.command.R.LoginUserCommand;
import test.project4v2.command.D.DeleteProductCommand;
import test.project4v2.dto.ProductDTO;
import test.project4v2.dto.UserDTO;
import test.project4v2.entity.User;
import test.project4v2.handler.CommandHandler;
import test.project4v2.handler.QueryHandler;
import test.project4v2.handler.query.D.DeleteProductQueryHandler;
import test.project4v2.handler.query.R.GetAllProductQueryHandler;
import test.project4v2.handler.query.R.GetProductByIdQueryHandler;
import test.project4v2.query.GetAllProductQuery;
import test.project4v2.query.GetProductsIdQuery;
import test.project4v2.repository.UserRepository;

import java.util.List;

@Component
public class MediatorImpl implements Mediator {
    private final UserRepository userRepository;
    private final ApplicationContext applicationContext;


    @Autowired
    private GetAllProductQueryHandler getAllProductsQueryHandler;

    @Autowired
    private GetProductByIdQueryHandler getProductByIdQueryHandler;

    @Autowired
    private DeleteProductQueryHandler deleteProductCommandHandler;

    @Autowired
    public MediatorImpl(UserRepository userRepository, ApplicationContext applicationContext, GetAllProductQueryHandler getAllProductQueryHandler) {
        this.userRepository = userRepository;
        this.applicationContext = applicationContext;
        this.getAllProductsQueryHandler = getAllProductQueryHandler;
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
    public List<ProductDTO> send(CreateUserCommand command) {
        UserDTO userDTO = command.getUserDTO();
        User user = new User(userDTO.getUsername(), userDTO.getPassword(), userDTO.getEmail(), userDTO.getPhone());
        userRepository.save(user);
        return null;
    }

    @Override
    public void send(LoginUserCommand command) {
        String username = command.getUsername();
        String password = command.getPassword();
        User user = userRepository.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {

        } else {
            throw new RuntimeException("Invalid username or password");
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
    public <T> T send(Object commandOrQuery) {
        if (commandOrQuery instanceof GetAllProductQuery) {
            return (T) getAllProductsQueryHandler.handle((GetAllProductQuery) commandOrQuery);
        }
        if (commandOrQuery instanceof GetProductsIdQuery) {
            return (T) getProductByIdQueryHandler.getHandle((GetProductsIdQuery) commandOrQuery);
        }
        if (commandOrQuery instanceof DeleteProductCommand) {
            deleteProductCommandHandler.getHandle((DeleteProductCommand) commandOrQuery);
            return null;
        }

        return null;
    }
}
