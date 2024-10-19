package test.project4v2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import test.project4v2.Mediator.Mediator;
import test.project4v2.dto.UserDTO;
import test.project4v2.query.GetUserQuery;

@RestController
@RequestMapping("/user")
public class UserController {

    private final Mediator mediator;
    @Autowired
    public UserController(Mediator mediator) {
        this.mediator = mediator;
    }

    @GetMapping("/{id}")
    public UserDTO getUser(@PathVariable Long id) {
        GetUserQuery query = new GetUserQuery(id);
        return mediator.send(query);
    }


}
