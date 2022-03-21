package com.example.simpleblog.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User implements Serializable {
    @Id
    private Integer id;

    private String nickname;
    private String login;
    private String password;

    public User(Integer id, String nickname, String login, String password) {
        this.id = id;
        this.nickname = nickname;
        this.login = login;
        this.password = password;
    }
}
