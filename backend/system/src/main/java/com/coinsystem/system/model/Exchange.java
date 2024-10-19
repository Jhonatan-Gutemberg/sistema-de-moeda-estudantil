package com.coinsystem.system.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Exchange {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "vantage_id", nullable = false)
    private Vantage vantage;

    private LocalDateTime date;
    private int coinsSpent;

    public Exchange(Student student, Vantage vantage, LocalDateTime date, int coinsSpent) {
        this.student = student;
        this.vantage = vantage;
        this.date = date;
        this.coinsSpent = coinsSpent;
    }

}
