
package org.example.project4.WebPacket.Domain.dto;

import lombok.Getter;
import lombok.Setter;
import org.example.project4.WebPacket.Domain.model.User;

@Getter
@Setter
public class UserDTO {
    private Long id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
    private String address;

    public UserDTO(User user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
    }
    // Getters and Setters
}