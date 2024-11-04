package test.project4v2.command.D;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import test.project4v2.Mediator.Mediator;

@Getter
@Setter
@NoArgsConstructor
public class RemoveFromCartCommand implements Mediator.Command<Void> {
    private Long userId;
    private Long productId;
}
