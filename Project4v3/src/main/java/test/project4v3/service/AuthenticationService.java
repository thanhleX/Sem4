package test.project4v3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import test.project4v3.JWT.JWTUtility;
import test.project4v3.dto.UserDTO;

import java.util.concurrent.Future;

@Service
public class AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final JWTUtility jwtTokenProvider;
    private final UserDetailsService userDetailsService;

    @Autowired
    public AuthenticationService(AuthenticationManager authenticationManager,
                                 JWTUtility jwtTokenProvider,
                                 UserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userDetailsService = userDetailsService;
    }

    public Future<String> login(UserDTO loginDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDTO.getUsername(), loginDTO.getPassword()));

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return jwtTokenProvider.generateToken(String.valueOf(userDetails));
    }
}
