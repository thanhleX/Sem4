package test.project4v2.handler.query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.project4v2.dto.UserDTO;
import test.project4v2.entity.User;
import test.project4v2.handler.QueryHandler;
import test.project4v2.query.GetUserQuery;
import test.project4v2.repository.UserRepository;
@Service
public class GetUserHandler implements QueryHandler<GetUserQuery,UserDTO> {

    private final UserRepository userRepository;

    @Autowired
    public GetUserHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDTO getHandle(GetUserQuery query) {
        User user = userRepository.findById(query.getUserId()).orElse(null);

        if (user == null) {
            return null; // Or throw a custom exception
        }
        // Using constructor to create UserDTO instance
        return new UserDTO(user.getId(), user.getName(), user.getAddress(),
                 user.getCreateDate(),user.getUpdateDate());
    }
}
