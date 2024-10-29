
package test.project4v2.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserDTO {
    private Long id;
    private String username;
    private String phone;
    private String address;
    private String email;



    public UserDTO(Long id, String name, String address, LocalDateTime createDate, LocalDateTime updateDate) {

    }


    // Getters and Setters
}