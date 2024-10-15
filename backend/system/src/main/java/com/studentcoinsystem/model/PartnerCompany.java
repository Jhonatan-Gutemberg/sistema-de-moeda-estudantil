package com.studentcoinsystem.model;

import java.util.List;
import java.util.ArrayList;

import jakarta.persistence.CascadeType;
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

    @OneToMany(mappedBy = "partnercompany", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Vantage> vantages = new ArrayList<>();

    public PartnerCompany(String name, String email, UsersType type, String phoneNumber, String password,
            String address, String cnpj) {
        super(name, email, type, phoneNumber, password, address);
        this.cnpj = cnpj;
    }

    

}
