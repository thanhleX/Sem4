package test.project4v2.command;

import lombok.Getter;
import lombok.Setter;
import test.project4v2.Mediator.Mediator;
import test.project4v2.dto.UserDTO;

@Getter
@Setter
public class LoginUserCommand implements Mediator.Command<UserDTO> {
    private String username;
    private String password;

    public LoginUserCommand(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
