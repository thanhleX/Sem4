package test.project4v2.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import test.project4v2.Mediator.Mediator;
import test.project4v2.dto.UserDTO;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserCommand implements Mediator.Command<UserDTO> {
    private String username;
    private String password;
    private String email;
    private String address;
    private String phoneNumber;
    private String role;

    public UserDTO getUserDTO() {


        return new UserDTO(username, password, email, address, phoneNumber);
    }
}
