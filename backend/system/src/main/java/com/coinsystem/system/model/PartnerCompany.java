package com.coinsystem.system.model;

import java.util.List;

import com.coinsystem.system.enums.UsersType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_parthercompany")
@Getter
@Setter
@NoArgsConstructor
public class PartnerCompany extends Users {
    @Column(name = "cnpj", nullable = false, unique = true, columnDefinition = "VARCHAR(255)")
    private String cnpj;

    @OneToMany
    private List<Vantage> advantages;

    public PartnerCompany(String name, String email, UsersType type, String phoneNumber, String password,
            String address, String cnpj) {
        super(name, email, type, phoneNumber, password, address);
        this.cnpj = cnpj;
    }

}
