package org.example.project4.WebPacket.Application.Controller.command;

import org.example.project4.WebPacket.Domain.dto.UserDTO;
import org.example.project4.WebPacket.Infastructure.service.command.UserCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/users")
public class UserCommandController {
    @Autowired
    private UserCommandService userCommandService;
    @PostMapping
    public CompletableFuture<?> createUser(@RequestBody UserDTO userDTO) {
         userCommandService.createUser(userDTO);
        return userCommandService.createUser(userDTO);
    }
    @PutMapping("/{id}")
    public CompletableFuture<?> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        userCommandService.updateUser( id ,userDTO);
        return userCommandService.updateUser(id, userDTO);
    }
    @PutMapping("/{id}")
    public CompletableFuture<?> deleteUser(@PathVariable Long id) {
        userCommandService.deleteUser(id);
        return userCommandService.deleteUser(id);
    }

}