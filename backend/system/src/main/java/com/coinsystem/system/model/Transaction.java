package com.coinsystem.system.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "tb_transaction")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "teacher", nullable = false)
    private Long TeacherId;

    @Column(name = "student_id", nullable = false)
    private Long studentId;

    @Column(name = "amount", nullable = false)
    private int amount;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "timestamp", nullable = false)
    private LocalDateTime timestamp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wallet_id", nullable = false)
    @JsonBackReference
    private Wallet wallet;

    public Transaction(Long TeacherId, Long studentId, int amount, String description) {
        this.TeacherId = TeacherId;
        this.studentId = studentId;
        this.amount = amount;
        this.description = description;
        this.timestamp = LocalDateTime.now();
    }
}
