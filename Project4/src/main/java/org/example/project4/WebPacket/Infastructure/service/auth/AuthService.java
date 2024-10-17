package org.example.project4.WebPacket.Infastructure.service.auth;

import org.example.project4.WebPacket.Domain.JWT.JWTUtility;
import org.example.project4.WebPacket.Domain.dto.UserDTO;
import org.example.project4.WebPacket.Domain.model.User;
import org.example.project4.WebPacket.Domain.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTUtility jwtUtility;
    @Async
    public CompletableFuture<UserDTO> registerUser(UserDTO userDTO) {
        if (userRepository.existsByUsername(userDTO.getUsername())) {
            throw new RuntimeException("Username or email already in use");
        }

        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword())); // Encrypt password
        user = userRepository.save(user);
        return CompletableFuture.completedFuture(new UserDTO(user));
    }
    @Async
    public CompletableFuture<String> login(UserDTO userDTO) throws Exception {
        try {
            // Authenticate the user
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userDTO.getUsername(), userDTO.getPassword())
            );
        } catch (Exception e) {
            throw new Exception("Invalid username or password", e);
        }

        // Load user details
        UserDetails userDetails = userDetailsService.loadUserByUsername(userDTO.getUsername());

        // Generate JWT token using UserDetails
        return jwtUtility.generateToken(userDetails);
    }
}
