package org.example.project4.WebPacket.Application.Controller.auth;

import org.example.project4.WebPacket.Domain.dto.UserDTO;
import org.example.project4.WebPacket.Infastructure.service.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/auth")
public class AuthCommandController {
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public CompletableFuture<String> login(@RequestBody UserDTO userDTO) throws Exception {
        return authService.login(userDTO);
    }
    @PostMapping("/register")
    public CompletableFuture<UserDTO> register(@RequestBody UserDTO userDTO) {

        return authService.registerUser(userDTO);
    }
}