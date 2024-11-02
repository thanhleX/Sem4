package test.project4v2.handler.query.D;

import org.springframework.stereotype.Component;
import test.project4v2.command.D.DeleteProductCommand;
import test.project4v2.handler.QueryHandler;
@Component
public class DeleteProductQueryHandler implements QueryHandler<DeleteProductCommand, Void> {
    @Override
    public Void getHandle(DeleteProductCommand query) {

        return null;
    }
}
