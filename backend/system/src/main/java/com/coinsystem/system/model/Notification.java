package com.coinsystem.system.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

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
     @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss", timezone = "America/Sao_Paulo")
    private LocalDateTime dateShipping;

    @Column(name = "date_receipt")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss", timezone = "America/Sao_Paulo")
    private LocalDateTime dateReceipt;

    @ManyToOne
    @JoinColumn(name = "id_partnercompany", nullable = false)
    private PartnerCompany partnerCompany;

    @ManyToOne
    @JoinColumn(name = "id_student", nullable = false)
    private Student student;

    @Column(name = "student_confirmed", nullable = false)
    private boolean studentConfirmed = false;

    @Column(name = "partner_confirmed", nullable = false)
    private boolean partnerConfirmed = false;

    public Notification(String coupon, PartnerCompany partnerCompany, Student student) {
        this.coupon = coupon;
        this.dateShipping = LocalDateTime.now();
        this.partnerCompany = partnerCompany;
        this.student = student;
    }
}