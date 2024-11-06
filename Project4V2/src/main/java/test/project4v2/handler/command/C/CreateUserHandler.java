package test.project4v2.handler.command.C;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import test.project4v2.command.C.CreateUserCommand;
import test.project4v2.dto.UserDTO;
import test.project4v2.entity.User;
import test.project4v2.handler.CommandHandler;
import test.project4v2.repository.UserRepository;
@Service
public class CreateUserHandler implements CommandHandler<CreateUserCommand, UserDTO>
{
    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    private PasswordEncoder passwordEncoder;
    @Override
    public UserDTO handle(CreateUserCommand command) {
//        if (userRepository.existsByUsername(command.getUsername())) {
//            throw new MessageAggregationException("User already exists");
//        }
        User user = new User();
        user.setUsername(command.getUsername());
//        user.setPassword(passwordEncoder.encode(command.getPassword()));
        user.setEmail(command.getEmail());
        user.setAddress(command.getAddress());
        user.setPhone(command.getPhoneNumber());
        userRepository.save(user);
        UserDTO userDTO = new UserDTO(user.getId(), user.getUsername(), user.getAddress(), user.getCreateDate(), user.getUpdateDate());
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        userDTO.setAddress(user.getAddress());
        return userDTO;
    }
}
