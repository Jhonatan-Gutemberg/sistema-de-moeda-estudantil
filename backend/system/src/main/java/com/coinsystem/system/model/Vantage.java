package com.coinsystem.system.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_vantage")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vantage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false, unique = true, columnDefinition = "VARCHAR(255)")
    private String name;
    @Column(name = "description", nullable = false, columnDefinition = "VARCHAR(255)")
    private String description;
    @Column(name = "value", nullable = false)
    private double value;

   // @ManyToOne
   // @JoinColumn(name = "partnercompany")
    //private PartnerCompany partnerCompany;

}
