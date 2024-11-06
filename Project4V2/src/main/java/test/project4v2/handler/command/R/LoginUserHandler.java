package test.project4v2.handler.command.R;

//import io.netty.handler.codec.MessageAggregationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import test.project4v2.command.R.LoginUserCommand;
import test.project4v2.dto.UserDTO;
import test.project4v2.handler.CommandHandler;
import test.project4v2.repository.UserRepository;

@Service
public class LoginUserHandler implements CommandHandler<LoginUserCommand, UserDTO> {

    private static final Logger logger = LoggerFactory.getLogger(LoginUserHandler.class);

    @Autowired
    private UserRepository userRepository;

    public UserDTO handle(LoginUserCommand command) {
        boolean isAuthenticated = userRepository.checkUser(command.getUsername(), command.getPassword());

//        if (isAuthenticated) {
            logger.info("User {} logged in successfully.", command.getUsername());
            return new UserDTO(command.getUsername());
//        } else {
//            logger.warn("Login failed for user {}: Invalid username or password.", command.getUsername());
//            throw new MessageAggregationException("Invalid username or password.");
//        }
    }

}