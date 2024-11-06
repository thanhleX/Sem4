package test.project4v2.command.D;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import test.project4v2.Mediator.Mediator;
import test.project4v2.entity.User;

@Getter
@Setter
@NoArgsConstructor
public class RemoveFromCartCommand implements Mediator.Command<Void> {
    private User userId;
    private Long productId;

    public RemoveFromCartCommand(User userId, Long productId) {
        this.userId = userId;
        this.productId = productId;
    }
}
