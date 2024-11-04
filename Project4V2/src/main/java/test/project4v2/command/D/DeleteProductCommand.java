package test.project4v2.command.D;

import lombok.Getter;
import lombok.Setter;
import test.project4v2.Mediator.Mediator;


@Getter
@Setter
public class DeleteProductCommand implements Mediator.Command<Void> {
    private Long id;

    public DeleteProductCommand(Long id) {
        this.id = id;
    }
}
