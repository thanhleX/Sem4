
package test.project4v2.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.userdetails.UserDetails;
import test.project4v2.entity.User;

import java.time.LocalDateTime;
import java.util.ArrayList;

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





    public <E> UserDTO(String username, String password, ArrayList<E> es) {
    }


    // Getters and Setters
}