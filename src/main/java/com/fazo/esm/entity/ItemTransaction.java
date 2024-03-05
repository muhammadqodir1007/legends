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
public class ItemTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private ItemType itemType;

    @ManyToOne
    private User user;

    @ManyToOne
    private Category category;

    private long quantity;
    private LocalDateTime actionDate;
    private String actionType;

}
