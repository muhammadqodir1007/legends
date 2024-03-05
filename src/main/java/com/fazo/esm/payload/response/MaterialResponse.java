package com.fazo.esm.payload.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MaterialResponse {
    private int id;
    private int materialTypeId;
    private long quantity;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private int materialCategoryId;
    private int userId;
}
