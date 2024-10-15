package com.studentcoinsystem.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "coupon", nullable = false, columnDefinition = "VARCHAR(255)")
    private String coupon;
    @Column(name = "id_sender", nullable = false, columnDefinition = "VARCHAR(50)")
    private Long idSender; // teacher
    @Column(name = "id_partnercompany", nullable = false, columnDefinition = "VARCHAR(50)")
    private Long idPartnerCompany;

}
