package test.project4v2.command.D;

import lombok.Getter;
import lombok.Setter;
import test.project4v2.command.C.CreateUserCommand;

@Getter
@Setter
public class DeleteProductCommand extends CreateUserCommand {
    private Long id;

    public DeleteProductCommand(Long id) {
        this.id = id;
    }
}
