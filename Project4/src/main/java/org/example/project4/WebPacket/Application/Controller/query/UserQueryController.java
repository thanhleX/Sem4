package org.example.project4.WebPacket.Application.Controller.query;

import jakarta.persistence.PostLoad;
import jakarta.persistence.PostUpdate;
import org.example.project4.WebPacket.Domain.model.User;
import org.example.project4.WebPacket.Infastructure.service.query.UserQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserQueryController {
    @Autowired
    private UserQueryService userQueryService;

    @GetMapping
    public List<User> getAllUsers() {
        return userQueryService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userQueryService.getUserById(id);
    }

}
