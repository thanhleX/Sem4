package org.example.project4.WebPacket.Controller.command;

import org.example.project4.WebPacket.JWT.JWTUtility;
import org.example.project4.WebPacket.dto.UserDTO;
import org.example.project4.WebPacket.model.User;
import org.example.project4.WebPacket.service.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public String login(@RequestBody UserDTO userDTO) throws Exception {
            return authService.login(userDTO);
    }
    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody UserDTO userDTO) {

        User registeredUser = authService.registerUser(userDTO);
        return ResponseEntity.ok(registeredUser);
    }
}