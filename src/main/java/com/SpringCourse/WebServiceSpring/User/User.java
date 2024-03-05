package com.SpringCourse.WebServiceSpring.User;

import com.SpringCourse.WebServiceSpring.Currency.Currency;
import jakarta.persistence.*;


import java.util.List;

@Entity
@Table(name = "PlatformUsers")
public class User {
    public User() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
    private String firstName;
    private String lastName;
    private String password;

    public User(Integer id, String firstName, String lastName, String password, String login) {
        Id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.login = login;
    }

    private String login;


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Currency> currencies;


    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
