package com.coinsystem.system.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_vantage")

@Getter
@Setter
@NoArgsConstructor
public class Vantage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", columnDefinition = "VARCHAR(255)")
    private String name;
    @Column(name = "description", columnDefinition = "VARCHAR(255)")
    private String description;
    @Column(name = "value")
    private int value;
    @Column(name = "couponCode")
    private String couponCode;

    @ManyToOne
    @JoinColumn(name = "id_PartnerCompany")
    private PartnerCompany partnerCompany;

    public Vantage(String name, String description, int value) {
        this.name = name;
        this.description = description;
        this.value = value;
    }

}
