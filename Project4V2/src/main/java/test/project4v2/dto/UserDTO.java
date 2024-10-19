
package test.project4v2.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    private Long id;
    private String username;
    private String password;
    private String phone;
    private String address;
    private String email;

    public UserDTO(Long userId, String username, String email, String address, String phone, String password) {
    }

    public UserDTO() {

    }


    // Getters and Setters
}