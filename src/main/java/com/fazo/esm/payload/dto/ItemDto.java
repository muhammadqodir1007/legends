package com.fazo.esm.payload.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemDto {
    private int adminId;
    private int categoryId;
    private int itemType;
    private long quantity;
    private String description;
}
