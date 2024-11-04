
package test.project4v2.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import test.project4v2.entity.User;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class UserDTO implements UserDetails {
    private Long id;
    private String username;
    private String phone;
    private String password;
    private String address;
    private String email;


    public UserDTO(String username, String password, String email, String address, String phoneNumber) {
        this.username = username;
        this.password = password;
    }

    public UserDTO(User userId) {
        this.id = userId.getId();
    }

    public UserDTO(Long id, String name, String address, LocalDateTime createDate, LocalDateTime updateDate) {


        this.id = id;
        this.username = name;
        this.address = address;

    }

    public UserDTO(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }


    // Getters and Setters
}