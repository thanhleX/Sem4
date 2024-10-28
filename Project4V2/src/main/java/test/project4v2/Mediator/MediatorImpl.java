package test.project4v2.Mediator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import test.project4v2.handler.CommandHandler;
import test.project4v2.handler.QueryHandler;

@Component
public class MediatorImpl implements Mediator {

    private final ApplicationContext applicationContext;

    @Autowired
    public MediatorImpl(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public <TResponse> TResponse send(Command<TResponse> command) {
        var handler = applicationContext.getBean(getHandlerClass(command));
        return ((CommandHandler<Command<TResponse>, TResponse>) handler).handle(command);
    }

    @Override
    public <TResponse> TResponse send(Query<TResponse> query) {
        var handler = applicationContext.getBean(getHandlerClass(query));
        return ((QueryHandler<Query<TResponse>, TResponse>) handler).getHandle(query);
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
