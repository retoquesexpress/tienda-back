package com.fpmislata.tienda_back.persistence.dao.jpa.entity;


import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "users")
public class UserJpaEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id_user;
    private String name;
    private String email;
    @Column(name = "user_name")
    private String userName;
    private String password;
    @Column(name = "phone_number")
    private String phoneNumber;
    private String address;
    @Column(name = "birth_date")
    private Date birthDate;
    private String role;

    public UserJpaEntity() {
    }
    public UserJpaEntity(String id_user, String name, String email, String userName, String password, String phoneNumber, String address, Date birthDate, String role) {
        this.id_user = id_user;
        this.name = name;
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.birthDate = birthDate;
        this.role = role;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof UserJpaEntity other)) {
            return false;
        }
        return (this.id_user != null || other.id_user == null) && (this.id_user == null || this.id_user.equals(other.id_user));
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id_user != null ? id_user.hashCode() : 0);
        return hash;
    }
}
