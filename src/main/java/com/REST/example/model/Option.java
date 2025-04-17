package com.REST.example.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "option")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Option {
    @Id
    @Column(name = "option_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "option_value")
    private String value;
}
