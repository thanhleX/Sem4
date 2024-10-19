package test.project4v2.handler.command;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import test.project4v2.command.CreateUserCommand;
import test.project4v2.dto.UserDTO;
import test.project4v2.entity.User;
import test.project4v2.handler.CommandHandler;
import test.project4v2.repository.UserRepository;
@Service
public class CreateUserHandler implements CommandHandler<CreateUserCommand, UserDTO>
{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public UserDTO handle(CreateUserCommand command) {
       User user = new User();
       user.setUsername(command.getUsername());
        user.setPassword(passwordEncoder.encode(command.getPassword()));
        user.setEmail(command.getEmail());
        user.setAddress(command.getAddress());
        user.setPhone(command.getPhoneNumber());
        userRepository.save(user);
        UserDTO UserDTO = new UserDTO();
        UserDTO.setId(user.getUserId());
        UserDTO.setUsername(user.getUsername());
        UserDTO.setEmail(user.getEmail());
        UserDTO.setAddress(user.getAddress());
        return UserDTO;
    }
}
