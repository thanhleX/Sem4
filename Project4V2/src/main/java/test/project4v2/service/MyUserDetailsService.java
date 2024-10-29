package test.project4v2.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import test.project4v2.dto.UserDTO;
import test.project4v2.entity.User;
import test.project4v2.repository.UserRepository;

import java.util.ArrayList;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public MyUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User userDTO = userRepository.findByUsername(username);
        if (userDTO == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return (UserDetails) new UserDTO(userDTO.getUsername(), userDTO.getPassword(), new ArrayList<>());
    }
}
