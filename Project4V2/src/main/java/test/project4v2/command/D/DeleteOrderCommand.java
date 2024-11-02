package test.project4v2.command.D;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import test.project4v2.Mediator.Mediator;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DeleteOrderCommand implements Mediator.Command<Void> {
    private Long orderId;
}
