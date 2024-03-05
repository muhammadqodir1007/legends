package com.fazo.esm.payload.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MaterialDto {
    private int adminId;
    private int materialCategoryId;
    private int materialTypeId;
    private long quantity;
    private String description;
}
