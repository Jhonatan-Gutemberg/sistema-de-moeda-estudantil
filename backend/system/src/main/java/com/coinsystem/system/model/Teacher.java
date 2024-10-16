package com.coinsystem.system.model;

import com.coinsystem.system.enums.UsersType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_teacher")
@Getter
@Setter
@NoArgsConstructor
public class Teacher extends Users {
    @Column(name = "salary", nullable = false)
    private double salary;
   @Column(name = "department", nullable = false)
    private String department;

    public Teacher(String name, String email, UsersType type, String phoneNumber, String password, String address,
            double salary, String department) {
        super(name, email, type, phoneNumber, password, address);
        this.salary = salary;
        this.department = department;
    }

}
