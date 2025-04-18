package com.REST.example.model;

import com.REST.example.validation.OnCreate;
import com.REST.example.validation.OnUpdate;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
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
    private Set<Option> options = new HashSet<>();
}
