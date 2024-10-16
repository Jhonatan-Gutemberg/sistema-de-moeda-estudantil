package com.coinsystem.system.model;

import java.util.Date;

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
@Table(name = "tb_classroom")

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Classroom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(255)")
    private String name;
    @Column(name = "capacity", nullable = false, columnDefinition = "VARCHAR(255)")
    private int capacity;
    @Column(name = "semester", nullable = false, columnDefinition = "VARCHAR(255)")
    private Date semester;

}