package test.project4v2.handler.query.R;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import test.project4v2.dto.UserDTO;
import test.project4v2.entity.User;
import test.project4v2.handler.QueryHandler;
import test.project4v2.repository.UserRepository;
@Component
public class GetUserQuery implements QueryHandler<test.project4v2.query.GetUserQuery,UserDTO> {

    private final UserRepository userRepository;

    @Autowired
    public GetUserQuery(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDTO getHandle(test.project4v2.query.GetUserQuery query) {
        User user = userRepository.findById(query.getUserId().getId()).orElse(null);

        if (user == null) {
            return null; // Or throw a custom exception
        }
        // Using constructor to create UserDTO instance
        return new UserDTO(user.getId(), user.getName(), user.getAddress(),
                 user.getCreateDate(),user.getUpdateDate());
    }
}
