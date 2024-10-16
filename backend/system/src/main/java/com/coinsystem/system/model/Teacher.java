package com.coinsystem.system.model;

import java.util.List;

import com.coinsystem.system.enums.UsersType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
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

    @OneToOne
    @JoinColumn(name = "id_wallet")
    private Wallet wallet;

    @ManyToOne
    @JoinColumn(name = "id_InstitutionEducation")
    private InstitutionEducation institutionEducation;
    
    @OneToMany
    private List<Student> students;

    public Teacher(String name, String email, UsersType type, String phoneNumber, String password, String address,
            double salary, String department) {
        super(name, email, type, phoneNumber, password, address);
        this.salary = salary;
        this.department = department;
    }

}
