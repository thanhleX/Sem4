
package test.project4v2.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Getter
@Setter
public class UserDTO {
    private Long id;
    private String username;
    private String phone;
    private String password;
    private String address;
    private String email;



    public UserDTO(Long id, String name, String address, LocalDateTime createDate, LocalDateTime updateDate) {

    }





    public <E> UserDTO(String username, String password, ArrayList<E> es) {
    }

    public UserDTO(String username) {

    }

    public UserDTO(String username, String password, String email, String address, String phoneNumber) {

    }


    // Getters and Setters
}