package com.fazo.esm.payload.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ItemResponse {
    private int id;
    private int itemType;
    private long quantity;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private int categoryId;
    private int userId;
}
