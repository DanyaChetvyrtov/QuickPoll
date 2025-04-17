package com.REST.example.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "poll")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Poll {
    @Id
    @Column(name = "poll_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "question")
    private String question;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "poll_id")
    @OrderBy
    private Set<Option> options;
}
