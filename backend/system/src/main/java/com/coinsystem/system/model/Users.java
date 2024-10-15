package com.coinsystem.system.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_users")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Inheritance(strategy = InheritanceType.JOINED)
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    @Column(name = "name", nullable = false, unique = true, columnDefinition = "VARCHAR(255)")
    protected String name;
    @Column(name = "email", nullable = false, unique = true, columnDefinition = "VARCHAR(50)")
    protected String email;
    @Enumerated(EnumType.STRING)
    protected UsersType type;
    @Column(name = "phoneNumber", nullable = false, columnDefinition = "VARCHAR(50)")
    protected String phoneNumber;
    @Column(name = "password", nullable = false, columnDefinition = "VARCHAR(255)")
    protected String password;
    @Column(name = "address", nullable = false, columnDefinition = "VARCHAR(255)")
    protected String address;

    public Users(String name, String email, UsersType type, String phoneNumber, String password, String address) {
        this.name = name;
        this.email = email;
        this.type = type;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.address = address;
    }

}

