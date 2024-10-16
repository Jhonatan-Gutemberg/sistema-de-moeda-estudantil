package com.coinsystem.system.model;

import com.coinsystem.system.enums.UsersType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_student")
@Getter
@Setter
@NoArgsConstructor
public class Student extends Users {
    @Column(name = "cpf", nullable = false, unique = true, columnDefinition = "VARCHAR(255)")
    private String cpf;
    @Column(name = "rg", nullable = false, unique = true, columnDefinition = "VARCHAR(255)")
    private String rg;

    @OneToOne
    private Wallet wallet;

    @ManyToOne
    private Teacher teacher;

    public Student(String name, String email, UsersType type, String phoneNumber, String password, String address,
            String cpf, String rg) {
        super(name, email, type, phoneNumber, password, address);
        this.cpf = cpf;
        this.rg = rg;
    }

}
