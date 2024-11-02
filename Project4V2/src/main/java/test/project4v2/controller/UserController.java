package test.project4v2.controller;


import jdk.internal.org.jline.utils.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import test.project4v2.Mediator.Mediator;
import test.project4v2.command.C.CreateUserCommand;
import test.project4v2.command.R.LoginUserCommand;
import test.project4v2.dto.UserDTO;
import test.project4v2.exception.Exception;
import test.project4v2.query.GetUserQuery;

@RestController
@RequestMapping("/user")
public class UserController {

    private final Mediator mediator;
    private Log logger;

    @Autowired
    public UserController(Mediator mediator) {
        this.mediator = mediator;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long id) {
        try {
            GetUserQuery query = new GetUserQuery(id);
            UserDTO userDTO = mediator.send(query);
            return ResponseEntity.ok(userDTO);
        } catch (Exception e) {
            Log.warn("User with ID {} not found.", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserDTO userDTO) {
        try {
            LoginUserCommand command = new LoginUserCommand(userDTO.getUsername(), userDTO.getPassword());
            mediator.send(command);
            return ResponseEntity.ok("Login successful.");
        } catch (Exception e) {
            Log.warn("Login failed for user {}: {}", userDTO.getUsername(), e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password.");
        } catch (java.lang.Exception e) {
            Log.error("An error occurred during login: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error during login.");
        }
    }
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserDTO userDTO) {
        try {
            CreateUserCommand command = new CreateUserCommand(userDTO.getUsername(), userDTO.getPassword() , userDTO.getEmail(), userDTO.getAddress() , userDTO.getPhone());
            mediator.send(command);
            return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully.");
        } catch (Exception e) {
            Log.error("Registration failed for user {}: {}", userDTO.getUsername(), e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Registration failed.");
        } catch (java.lang.Exception e) {
            Log.error("An error occurred during registration: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error during registration.");
        }

    }


}
