package test.project4v2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import test.project4v2.dto.UserDTO;
import test.project4v2.entity.User;
import test.project4v2.repository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userEntity = userRepository.findByUsername(username);
        if (userEntity == null) {
            throw new UsernameNotFoundException("User not found");
        }

        // Create a UserDTO with the user's details
        return new UserDTO(userEntity.getUsername(), userEntity.getPassword(), userEntity.getEmail(), userEntity.getAddress(), userEntity.getPhone());
    }
}
