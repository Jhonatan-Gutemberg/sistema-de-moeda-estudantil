package com.coinsystem.system.model;

import com.coinsystem.system.enums.UsersType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_institutioneducation")
@Getter
@Setter
@NoArgsConstructor
public class InstitutionEducation extends Users {
    @Column(name = "cnpj", nullable = false, unique = true, columnDefinition = "VARCHAR(255)")
    private String cnpj;


    public InstitutionEducation(String name, String email, UsersType type, String phoneNumber, String password,
            String address, String cnpj) {
        super(name, email, type, phoneNumber, password, address);
        this.cnpj = cnpj;
    }

    

}