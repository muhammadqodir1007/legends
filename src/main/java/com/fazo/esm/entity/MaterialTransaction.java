package com.fazo.esm.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class MaterialTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private MaterialType materialType;

    @ManyToOne
    private User user;

    @ManyToOne
    private MaterialCategory category;

    private long quantity;
    private LocalDateTime actionDate;
    private String actionType;

}
