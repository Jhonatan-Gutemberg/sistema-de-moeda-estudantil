package com.coinsystem.system.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_notification")

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
    @Column(name = "date_shipping")
    private LocalDate dateShipping;
    @Column(name = "date_receipt")
    private LocalDate dateReceipt;
    @Column(name = "id_sender", nullable = false, columnDefinition = "VARCHAR(50)")
    private Long idSender; // teacher
    @Column(name = "id_partnercompany", nullable = false, columnDefinition = "VARCHAR(50)")
    private Long idPartnerCompany;

}
