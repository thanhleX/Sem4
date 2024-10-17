package org.example.project4.WebPacket.Infastructure.service.command;

import org.example.project4.WebPacket.Domain.dto.UserDTO;
import org.example.project4.WebPacket.Domain.model.User;
import org.example.project4.WebPacket.Domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class UserCommandService {
    @Autowired
    private UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Async
    public CompletableFuture<Void> createUser(UserDTO userDTO) {
        return CompletableFuture.runAsync(() -> {
            User user = new User();
            user.setUsername(userDTO.getUsername());
            user.setPassword(passwordEncoder.encode(userDTO.getPassword())); // Encrypt password
            user.setFirstName(userDTO.getFirstName());
            user.setLastName(userDTO.getLastName());
            user.setPhone(userDTO.getPhone());
            user.setAddress(userDTO.getAddress());

            userRepository.save(user);
        });
    }
    @Async
    public CompletableFuture<Void> updateUser(Long id, UserDTO userDTO) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.setUsername(userDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setPhone(userDTO.getPhone());
        user.setAddress(userDTO.getAddress());
        userRepository.save(user);
        return CompletableFuture.completedFuture(null);
    }
    @Async
    public CompletableFuture<Void> deleteUser(Long userId) {
        userRepository.deleteById(userId);
        return CompletableFuture.completedFuture(null);
    }

}
