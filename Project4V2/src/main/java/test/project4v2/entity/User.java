package test.project4v2.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;
import test.project4v2.entity.Entities;

import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "User")

public class User extends Entities {

    @Column(name = "name")
    private String name;
    @Column(name = "username")
    private String username;
    @Column(name = "address")
    private String address;
    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;
    @Column(name = "role")
    private String role;

    public User
            (Long id, LocalDateTime createDate, LocalDateTime updateDate, String name, String address) {
        super(id, createDate, updateDate);
        this.name = name;
        this.address = address;
    }

    public User(Long id, String name, String address) {
        super(id);
        this.name = name;
        this.address = address;
    }

    public User(String username) {
        this.username = username;
    }

    public User(String username, String password, String email, String phone) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
    }
}
