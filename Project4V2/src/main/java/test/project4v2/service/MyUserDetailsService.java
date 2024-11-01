package test.project4v2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import test.project4v2.dto.UserDTO;
import test.project4v2.entity.User;
import test.project4v2.entity.UserDetailImpl;
import test.project4v2.repository.UserRepository;

import java.util.ArrayList;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User userDTO = userRepository.findByUsername(username);
        if (userDTO == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return (UserDetails) new UserDTO(userDTO.getUsername(), userDTO.getPassword(), new ArrayList<>());
    }
}
