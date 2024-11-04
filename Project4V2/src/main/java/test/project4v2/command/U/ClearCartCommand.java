package test.project4v2.command.U;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import test.project4v2.Mediator.Mediator;
import test.project4v2.dto.CartDTO;

import test.project4v2.entity.User;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClearCartCommand implements Mediator.Command<CartDTO> {
    private User userId;
}
