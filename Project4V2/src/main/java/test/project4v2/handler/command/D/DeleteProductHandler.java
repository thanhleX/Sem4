package test.project4v2.handler.command.D;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import test.project4v2.command.D.DeleteProductCommand;
import test.project4v2.handler.CommandHandler;
import test.project4v2.repository.ProductRepository;

@Component
public class DeleteProductHandler implements CommandHandler<DeleteProductCommand, Void> {

    @Autowired
    private ProductRepository productRepository;

    public Void handle(DeleteProductCommand command) {
        Long productId = command.getId();
        productRepository.deleteById(productId);
        System.out.println("Product deleted successfully.");
        return null;
    }
}
