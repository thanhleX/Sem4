package org.example.project4.WebPacket.Controller.command;

import org.example.project4.WebPacket.dto.UserDTO;
import org.example.project4.WebPacket.model.User;
import org.example.project4.WebPacket.service.UserService;
import org.example.project4.WebPacket.service.command.UserCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserCommandController {
    @Autowired
    private UserCommandService userCommandService;
    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserDTO userDTO) {
         userCommandService.createUser(userDTO);
        return ResponseEntity.ok().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        userCommandService.updateUser( id ,userDTO);
        return ResponseEntity.ok().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        userCommandService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

}